import { useState, useEffect } from "react";
import { StatLeaderboard } from "../types/StatLeader";
import "./TournamentEdition.css";
import { Link } from "react-router-dom";
interface StatLeadersBlockProps {
	id: string;
	type: string;
}

function StatLeadersBlock( {id, type}: StatLeadersBlockProps ) {
	const [statLeaders, setStatLeaders] = useState<StatLeaderboard[] | null>(null);


	useEffect(() => {
		async function getStatLeaders() {
			const response = await fetch(
				`http://localhost:8080/stats/${type}/${id}/leaders`
			);
			const data = await response.json();
			console.log(data);
			setStatLeaders(data);
		}
		getStatLeaders();
	}, [id, type]);
	
	if(statLeaders == null) {
		return <p>Loading Stat Leaders</p>; 
	}

	return(
		<div className="statLeadersBlock">
			{statLeaders.map((statLeader) => (
				<Link 
					className="statLeaderboard" 
					key={statLeader.name} 
					to={`leaderboards/${statLeader.statType}?sort=${statLeader.statName}&ascending=false`} >
					<h3>{statLeader.name}</h3>
					{statLeader.entries.map((entry) => (
						<h5 key={entry.entry}>{entry.entry}. {entry.player.nickname}: {entry.stat.toFixed(statLeader.decimalPlaces)} {statLeader.statUnit}</h5>
					))}
				</Link>
			))}
		</div>
	)
}

export default StatLeadersBlock
