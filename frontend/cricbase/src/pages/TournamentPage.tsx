import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { Tournament } from "../types/Tournament";
import TournamentEditionSearchButton from "../components/TournamentEditionSearchButton";
import StatLeadersBlock from "../components/StatLeadersBlock";

function TournamentPage() {
	const { id } = useParams();
	const [tournament, setTournament] = useState<Tournament | null>(null);

	useEffect(() => {
		async function getTournament() {
			const response = await fetch(`http://localhost:8080/tournaments/${id}`);
			const data = await response.json();
			setTournament(data);
		}

		getTournament();
	}, [id]);
	if(id == null) {
		return <p>Invalid ID!</p>
	}

	if(tournament == null) {
		return <p>Loading...</p>
	}

	return (
		<div>	
			<h1>{tournament.name}</h1>
			<div className="editionList">
				{tournament.editions.map((edition) => (
					<TournamentEditionSearchButton tournamentEdition={edition} />
				))}
			</div>
			<div className="leaderList">
				<StatLeadersBlock id={id} type="tournaments"/>
			</div>
		</div>
	)
}

export default TournamentPage
