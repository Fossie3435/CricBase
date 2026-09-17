import { useState, useEffect } from "react";
import { StatLeader } from "../types/StatLeader";
import "./TournamentEdition.css";
interface StatLeadersBlock {
	id: string;
}

function StatLeadersBlock( {id}: StatLeadersBlock ) {
	const [statLeaders, setStatLeaders] = useState<StatLeader[] | null>(null);


	useEffect(() => {
		async function getStatLeaders() {
			const response = await fetch(
				`http://localhost:8080/stats/editions/${id}/leaders`
			);
			const data = await response.json();
			setStatLeaders(data);
		}
		getStatLeaders();
	}, [id]);
	
	if(id == undefined) {
		return <p></p>
	}
	if(statLeaders == null) {
		return <p>Loading Stat Leaders</p>; 
	}

	return(
		<div className="statLeadersBlock">
			{statLeaders.map((statLeader) => (
				<div className="statLeaderboard" key={statLeader.name}>
					<h3>{statLeader.name}</h3>
					{statLeader.entries.map((entry) => (
						<h5 key={entry.entry}>{entry.entry}. {entry.player.nickname}: {entry.stat.toFixed(statLeader.decimalPlaces)} {statLeader.statName}</h5>
					))}
				</div>
			))}
		</div>
	)
}

export default StatLeadersBlock
