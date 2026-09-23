import { useEffect, useState } from "react";
import { Player } from "../types/Player";

function PlayersMissingNicknames() {
	const [players, setPlayers] = useState<Player[]>([])
	const [nicknames, setNicknames] = useState<Record<string, string>>({});

	useEffect(() => {
		async function getPlayers() {
			const response = await fetch(`http://localhost:8080/players/missingnicknames`);
			const data = await response.json();

			setPlayers(data);
		}
		getPlayers();
	}, []);
	async function addNickname(player: Player) {
		await fetch("http://localhost:8080/players/addnickname", {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify({
				id: player.id,
				nickname: nicknames[player.id] ?? ""
			})
		});
	}
	return (
		<>
			{players.map((player) => (
				<>
				<h3>{player.uniqueName} | {player.name}</h3>
				<input
					type="text"
					placeholder="Nickname"
					value={nicknames[player.id] ?? ""}
					onChange={(e) =>
						setNicknames({
							...nicknames,
							[player.id]: e.target.value
							})
						}
					/>

					<button onClick={() => addNickname(player)}>
					Add Nickname
					</button>
					</>
			))}
		</>
	)
}

export default PlayersMissingNicknames;
