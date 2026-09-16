import { useEffect, useState } from "react";
import { Tournament } from "../types/Tournament";
import TournamentEditionSearchButton from "../components/TournamentEditionSearchButton";



function TournamentSearchPage() {
	const [tournaments, setTournaments] = useState<Tournament[] | null>(null);
	useEffect(() => {
	async function getTournaments() {
		const response = await fetch(
		"http://localhost:8080/tournaments"
		);
		const data = await response.json();
		console.log(data);
		setTournaments(data);
	}

	getTournaments();
	}, []);

	if(tournaments == null) {
		return <p>Loading...</p>
	}
	return (
		<>
			{tournaments.map((tournament) => {
				return <div key={tournament.id}>
				<h2 key={tournament.id}>{tournament.name}</h2>
				{tournament.editions.map((tournamentEdition) => (
					<TournamentEditionSearchButton tournamentEdition={tournamentEdition} />		
				))}
				</ div>
			})}
		</>
	)
}

export default TournamentSearchPage
