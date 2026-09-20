import { useState, useEffect } from "react";
import { useParams, useSearchParams } from "react-router-dom";
import { BowlingLeaderboardEntry } from "../types/Statline";
import './Leaderboard.css';
import { getShortBowlingPeformanceSummary } from '../utils/bowlingPerformance'

function BowlingLeaderboard() {
	const { id } = useParams();
	const [searchParams, setSearchParams] = useSearchParams();
	const sortColumn = searchParams.get("sort") ?? "wickets";
	const ascending = searchParams.get("ascending") === "true";	
	const [leaderboard, setLeaderboard] = useState<BowlingLeaderboardEntry[] | null>(null);
	
	function sortLeaderboard(column: string) {
		if(sortColumn === column) {
			setSearchParams({sort: column, ascending: String(!ascending)});
		} else {
			setSearchParams({sort: column, ascending: "false"});
		}
	}

	if(id == undefined) {
		return <p>Invalid ID!</p>
	}

	useEffect(() =>  {
		async function getLeaderboard() {
			const response = await fetch(
				`http://localhost:8080/stats/editions/${id}/leaderboards/bowling`
			);
			const data = await response.json();
			console.log(data);
			setLeaderboard(data);
		}
		getLeaderboard();

	}, [id]);

	if(leaderboard === null) {
		return <p>Invalid leaderboard!</p>
	}

	const sortedLeaderboard = [...leaderboard].sort((a, b) => {
		let result: number;
		switch (sortColumn) {
			case "player":
				result = a.player.nickname.localeCompare(b.player.nickname);
				break;
			case "matches":
				result = b.statLine.matches - a.statLine.matches;
				break;
			case "innings":
				result = b.statLine.innings - a.statLine.innings;
				break;
			case "wickets":
				result = b.statLine.wickets - a.statLine.wickets;
				break;
			case "maidens":
				result = b.statLine.maidens - a.statLine.maidens;
				break;
			case "balls":
				result = b.statLine.ballsBowled - a.statLine.ballsBowled;
				break;
			case "runs":
				result = b.statLine.runsConceded - a.statLine.runsConceded;
				break;
			case "wides":
				result = b.statLine.wides - a.statLine.wides;
				break;
			case "noballs":
				result = b.statLine.noBalls - a.statLine.noBalls;
				break;
			case "fours":
				result = b.statLine.foursConceded - a.statLine.foursConceded;
				break;
			case "sixes":
				result = b.statLine.sixesConceded - a.statLine.sixesConceded;
				break;
			case "ER":
				result = a.statLine.economyRate - b.statLine.economyRate;
				break;
			case "average":
				result = a.statLine.average - b.statLine.average;
				break;
			case "best":
				result = b.statLine.best.wicketsTaken - a.statLine.best.wicketsTaken;
				if(!result) {
					result = a.statLine.best.runsConceded - b.statLine.best.runsConceded;
				}
				break;
			default:
				return 0;
		}
		if(ascending) {
			result = result * -1;
		}
		return result;
	})

	return (
		<div className="leaderboard">
			<h1>Batting Stats</h1>
			<table>
				<thead>
					<tr>
						<th onClick={() => sortLeaderboard("player")}>Player{sortColumn === "player" && (ascending ? "↑" : "↓")}</th>
						<th onClick={() => sortLeaderboard("matches")}>Matches{sortColumn === "matches" && (ascending ? "↑" : "↓")}</th>
						<th onClick={() => sortLeaderboard("innings")}>Innings{sortColumn === "innings" && (ascending ? "↑" : "↓")}</th>
						<th onClick={() => sortLeaderboard("ER")}>Economy{sortColumn === "ER" && (ascending ? "↑" : "↓")}</th>
						<th onClick={() => sortLeaderboard("average")}>Average{sortColumn === "average" && (ascending ? "↑" : "↓")}</th>
						<th onClick={() => sortLeaderboard("wickets")}>Wickets{sortColumn === "wickets" && (ascending ? "↑" : "↓")}</th>
						<th onClick={() => sortLeaderboard("balls")}>Balls{sortColumn === "balls" && (ascending ? "↑" : "↓")}</th>
						<th onClick={() => sortLeaderboard("runs")}>Runs{sortColumn === "runs" && (ascending ? "↑" : "↓")}</th>
						<th onClick={() => sortLeaderboard("best")}>Best{sortColumn === "best" && (ascending ? "↑" : "↓")}</th>
						<th onClick={() => sortLeaderboard("maidens")}>Maidens{sortColumn === "maidens" && (ascending ? "↑" : "↓")}</th>
						<th onClick={() => sortLeaderboard("wides")}>Wides{sortColumn === "wides" && (ascending ? "↑" : "↓")}</th>
						<th onClick={() => sortLeaderboard("noballs")}>No Balls{sortColumn === "noballs" && (ascending ? "↑" : "↓")}</th>
						<th onClick={() => sortLeaderboard("fours")}>Fours{sortColumn === "fours" && (ascending ? "↑" : "↓")}</th>
						<th onClick={() => sortLeaderboard("sixes")}>Sixes{sortColumn === "sixes" && (ascending ? "↑" : "↓")}</th>
					</tr>
				</thead>
				<tbody>
					{sortedLeaderboard.map((e) => (
						<tr>
							<td>{e.player.nickname}</td>
							<td>{e.statLine.matches}</td>
							<td>{e.statLine.innings}</td>
							<td>{e.statLine.economyRate.toFixed(2)}</td>
							<td>{e.statLine.average.toFixed(2)}</td>
							<td>{e.statLine.wickets}</td>
							<td>{e.statLine.ballsBowled}</td>
							<td>{e.statLine.runsConceded}</td>
							<td>{getShortBowlingPeformanceSummary(e.statLine.best)}</td>	
							<td>{e.statLine.maidens}</td>
							<td>{e.statLine.wides}</td>
							<td>{e.statLine.noBalls}</td>
							<td>{e.statLine.foursConceded}</td>
							<td>{e.statLine.sixesConceded}</td>
							<td></td>
						</tr>
					))}
				</tbody>
			</table>
		</div>
	)


}

export default BowlingLeaderboard
