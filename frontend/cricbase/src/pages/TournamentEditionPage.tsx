import { useEffect, useState } from "react";
import { DetailedTournamentEdition } from "../types/Tournament";
import { useParams } from "react-router-dom";
import MatchSummary from "../components/MatchSummary";
import StatLeadersBlock from "../components/StatLeadersBlock";
import PointsTable from "../components/PointsTable";
import "../components/TournamentEdition.css";

function TournamentEditionPage() {
	const [edition, setEdition] = useState<DetailedTournamentEdition | null>(null)
	const { id } = useParams();

	useEffect( () => {
		async function getEdition() {
			const response = await fetch(
			`http://localhost:8080/tournaments/edition/${id}`
			);
			const data = await response.json();
			setEdition(data);
		}
		
		getEdition()
	}, [id]);
	
	if(id == undefined) {
		return <p>Invalid ID!</p>
	}
	if(edition == null) {
		return <p>Loading...</p>
	}

	return (
		<div className="editionPage">
			<div className="topBar">
				<div className="info">
					<h1>{edition.name} {edition.season}</h1>
					<h5>Edition: {edition.edition}</h5>
					<h5>Start: {edition.start.toString()}</h5>
					<h5>End: {edition.end.toString()}</h5>
				</div>
				<div className="pointsTable">
					<PointsTable key={"EditionPoints" + edition.id} pointsTable={edition.pointsTable} />
				</div>
			</div>
			<div className="page">
				<div className="matchList">	
				{edition.matches.map((match) => {
					return <MatchSummary key={"match" + match.id} match={match} />
				})}
				</div>
				<div>
					<StatLeadersBlock id={id} type="editions" />
				</div>
			</div>
		</div>
	)
}

export default TournamentEditionPage;
