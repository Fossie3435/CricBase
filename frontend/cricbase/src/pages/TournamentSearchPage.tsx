import { useEffect, useState } from "react";
import { Tournament } from "../types/Tournament";
import TournamentEditionSearchButton from "../components/TournamentEditionSearchButton";
import TournamentAdd from "../components/TournamentAdd";
import { Link } from "react-router-dom";



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
			{tournaments.map((tournament) => (
				<div key={tournament.id}>
					<Link key={tournament.id} to={`/tournaments/${tournament.id}`} ><h2>{tournament.name}</h2></Link>
					<br/>
				{tournament.editions.map((tournamentEdition) => (
					<TournamentEditionSearchButton tournamentEdition={tournamentEdition} />		
				))}
				</ div>
			))}

			<h1>Add Tournament</h1>
			<TournamentAdd />
		</>
	)
}

export default TournamentSearchPage
