import { useState } from "react";

type Roster = {
	name: string;
	tricode: string;
	organisationId?: number | null;
};

type Edition = {
	season: string;
	rosters: Roster[];
};

export default function TournamentForm() {
	const [folderName, setFolderName] = useState("");
	const [name, setName] = useState("");
	const [gender, setGender] = useState("");
	const [editions, setEditions] = useState<Edition[]>([
		{
			season: "",
			rosters: [{ name: "", tricode: "" }]
		}
	]);

	function addEdition() {
		setEditions([
			...editions,
			{
				season: "",
				rosters: [{ name: "", tricode: "" }]
			}
		]);
	}

	function addEditionWithPreviousTeams() {
		const previousRosters = editions.at(-1)?.rosters ?? [];
		setEditions([...editions, {
			season: "",
			rosters: structuredClone(previousRosters)
		}
		]);
	}

	function removeEdition(editionIndex: number) {
		setEditions(editions.filter((_, i) => i !== editionIndex));
	}

	function updateEdition(
		editionIndex: number,
		field: keyof Edition,
		value: string
	) {
		const updated = [...editions];
		updated[editionIndex] = {
			...updated[editionIndex],
			[field]: value
		};
		setEditions(updated);
	}

	function addRoster(editionIndex: number) {
		const updated = [...editions];

		updated[editionIndex].rosters.push({
			name: "",
			tricode: ""
		});

		setEditions(updated);
	}

	function removeRoster(editionIndex: number, rosterIndex: number) {
		const updated = [...editions];

		updated[editionIndex].rosters =
			updated[editionIndex].rosters.filter(
				(_, i) => i !== rosterIndex
			);

		setEditions(updated);
	}

	function updateRosterName(
		editionIndex: number,
		rosterIndex: number,
		name: string
	) {
		const updated = [...editions];

		updated[editionIndex].rosters[rosterIndex].name = name;

		setEditions(updated);
	}
	function updateRosterTricode(
		editionIndex: number,
		rosterIndex: number,
		tricode: string	
	) {
		const updated = [...editions];

		updated[editionIndex].rosters[rosterIndex].tricode = tricode;

		setEditions(updated);
	}

	function updateRosterOrganisation(
		editionIndex: number,
		rosterIndex: number,
		organisationId: number | null
	) {
		const updated = [...editions];
		updated[editionIndex].rosters[rosterIndex].organisationId = organisationId;
		setEditions(updated);
	}

	async function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
		event.preventDefault();

		const tournament = {
			folderName,
			name,
			gender,
			editions
		};

		console.log(JSON.stringify(tournament, null, 2));

		const response = await fetch("http://localhost:8080/tournaments", {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(tournament)
		});

		if (!response.ok) {
			console.error("Failed to create tournament");
			return;
		}

		console.log("Tournament created");
	}

	return (
		<form onSubmit={handleSubmit}>
			<h1>Create Tournament</h1>

			<label>
				Folder Name
				<input
					type="text"
					value={folderName}
					onChange={(e) => setFolderName(e.target.value)}
				/>
			</label>

			<br />

			<label>
				Name
				<input
					type="text"
					value={name}
					onChange={(e) => setName(e.target.value)}
				/>
			</label>

			<br />

			<label>
				Gender
				<select
					value={gender}
					onChange={(e) => setGender(e.target.value)}
				>
					<option value="">Select gender</option>
					<option value="male">Men</option>
					<option value="female">Women</option>
					<option value="mixed">Mixed</option>
				</select>
			</label>

			<hr />

			<h2>Editions</h2>

			{editions.map((edition, editionIndex) => (
				<fieldset key={editionIndex}>
					<legend>
						Edition {editionIndex + 1}
					</legend>

					<label>
						Season
						<input
							type="text"
							value={edition.season}
							onChange={(e) =>
								updateEdition(
									editionIndex,
									"season",
									e.target.value
								)
							}
						/>
					</label>

					<h3>Rosters</h3>

					{edition.rosters.map((roster, rosterIndex) => (
						<div key={rosterIndex}>
							<input
								type="text"
								placeholder="Roster name"
								value={roster.name}
								onChange={(e) =>
									updateRosterName(
										editionIndex,
										rosterIndex,
										e.target.value
									)
								}
							/>
							<input 
								type="text"
								placeholder="tricode"
								value={roster.tricode}
								onChange={(e) => 
									updateRosterTricode(
										editionIndex,
										rosterIndex,
										e.target.value
									)
								}
							/>

							<input
								type="number"
								placeholder="Organisation ID"
								value={roster.organisationId ?? ""}
								onChange={(e) => 
									updateRosterOrganisation(
										editionIndex,
										rosterIndex,
										e.target.value === "" ? null : Number(e.target.value)
									)
								}
							/>

							<button
								type="button"
								onClick={() =>
									removeRoster(
										editionIndex,
										rosterIndex
									)
								}
							>
								Remove
							</button>
						</div>
					))}

					<button
						type="button"
						onClick={() => addRoster(editionIndex)}
					>
						Add Roster
					</button>

					<br />

					<button
						type="button"
						onClick={() => removeEdition(editionIndex)}
					>
						Remove Edition
					</button>
				</fieldset>
			))}

			<br />

			<button type="button" onClick={addEdition}>
				Add Edition
			</button>
			<button type="button" onClick={addEditionWithPreviousTeams}>
				Add Edition with previous teams
			</button>

			<br />
			<br />

			<button type="submit">
				Create Tournament
			</button>
		</form>
	);
}
