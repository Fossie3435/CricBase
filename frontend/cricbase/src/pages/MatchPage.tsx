import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { DetailedMatchSummary } from "../types/Match";
import  MatchSummary  from "../components/MatchSummary.tsx";
import InningSummary from "../components/InningSummary.tsx";
import TeamSummaryComponent from "../components/TeamSummary.tsx";
import "./MatchPage.css";

function MatchPage() {
	const [match, setMatch] = useState<DetailedMatchSummary | null>(null)
	const { id } = useParams();
	
	useEffect( () => {
		async function getEdition() {
			const response = await fetch(
			`http://localhost:8080/matches/${id}`
			);
			const data = await response.json();
			console.log(data);
			setMatch(data);
		}
		
		getEdition()
	}, [id]);
	if(match == null) {
		return <p>Loading...</p>
	}

	return( 
		<div className="matchPage">
			<MatchSummary match={match.matchSummary} />
			{match.innings.map((inning) => (
				<InningSummary inning={inning} teams={match.teams} />
			))}

			<div className="teams">
				{match.teams.map((team) => (
					<TeamSummaryComponent team={team} />
				))}
			</div>
		</div>
	)
}

export default MatchPage
