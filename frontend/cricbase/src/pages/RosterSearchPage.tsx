import { useEffect, useState } from "react"
import { Roster } from "../types/Roster";
import RosterList from "../components/RosterList";
import { TournamentEdition } from "../types/Tournament";

interface RosterTournamentEditionEntry {
	tournamentEditionSummary: TournamentEdition;
	rosterSummaries: Roster[];
}

function RosterSearchPage() {
	const [rosters, setRosters] = useState<RosterTournamentEditionEntry[] | null>(null);

	async function handleSubmit(event: React.SubmitEvent<HTMLFormElement>) {
		event.preventDefault();
		
		const formData = new FormData(event.currentTarget);

		const roster = {
			name: formData.get("name"),
			tournamentEditionId: formData.get("tournamentEditionId")
		} 
		await fetch("http://localhost:8080/rosters", {
       		method: "POST",
        	headers: {
            	"Content-Type": "application/json"
        	},
        	body: JSON.stringify(roster)
    	});
	}

    useEffect(() => {
        async function fetchRosters() {
            const response = await fetch(
                "http://localhost:8080/rosters/getall"
            );

            const data: RosterTournamentEditionEntry[] = await response.json();
			console.log(data);
            setRosters(data);
        }

        fetchRosters();
    }, []);
	if(rosters === null) {
		return <p>Loading...</p>
	}

	return (
		<div>
			{rosters.map(roster => (
				<div>
					<h3>{roster.tournamentEditionSummary.name} {roster.tournamentEditionSummary.season}</h3>
					<div className="rosterList">
						{roster.rosterSummaries.map(rosterSummaries => (
							<RosterList roster={rosterSummaries} />
						))}
					</div>
				</div>
			))}	
						<h3>Add a Roster</h3>

			<form onSubmit={handleSubmit}>
				<label htmlFor="name">Roster Name</label>
				<input type="text" name="name" />
				<br/>
				<label htmlFor="tournamentEditionId">Tournament Edition ID</label>
				<input type="number" name="tournamentEditionId" />
				<br/>

				<button type="submit">Submit</button>
			</form>

		</div>	

	)
}

export default RosterSearchPage
