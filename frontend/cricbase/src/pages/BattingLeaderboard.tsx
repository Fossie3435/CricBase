import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import { useParams } from "react-router-dom"
import { BattingLeaderboardEntry } from "../types/Statline";
import './Leaderboard.css';
import { getShortBattingPerformanceSummary } from "../utils/battingPerformance";

interface BattingLeaderboardProps {
	type: string;
}

function BattingLeaderboard( {type}: BattingLeaderboardProps ) {
	const { id } = useParams();
	const [searchParams, setSearchParams] = useSearchParams();
	const sortColumn = searchParams.get("sort") ?? "runs";
	const ascending = searchParams.get("ascending") === "true";	
	const [leaderboard, setLeaderboard] = useState<BattingLeaderboardEntry[]>([]);

	
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
	if(type == undefined || (type != "tournaments" && type != "editions")) {
		return <p>Invalid Type!</p>
	}	

	useEffect(() =>  {
		async function getLeaderboard() {
			const response = await fetch(
				`http://localhost:8080/stats/${type}/${id}/leaderboards/batting`
			);
			const data = await response.json();
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
			case "runs":
				result = (b.statLine.runs - a.statLine.runs);
				break;
			case "balls":
				result = (b.statLine.ballsFaced - a.statLine.ballsFaced);
				break;	
			case "matches":
				result = (b.statLine.matches - a.statLine.matches);
				break;
			case "innings":
				result = (b.statLine.innings - a.statLine.innings);
				break;
		 	case "notouts":
				result = (a.statLine.dismissals - b.statLine.dismissals);
				break;
			case "fours":
				result = (b.statLine.fours - a.statLine.fours);
				break;
			case "sixes":
				result = (b.statLine.sixes - a.statLine.sixes);
				break;
			case "player":
				result = a.player.nickname.localeCompare(b.player.nickname);
				break;
			case "strikeRate":
				result = b.statLine.strikeRate - a.statLine.strikeRate;
				break;
			case "average":
				result = b.statLine.average - a.statLine.average;
				break;
			case "best":
				result = b.statLine.best.runs - a.statLine.best.runs;
				if(!result) {
					result = Number(a.statLine.best.dismissed) - Number(b.statLine.best.dismissed);
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
						<th onClick={() => sortLeaderboard("notouts")}>Not Outs{sortColumn === "notouts" && (ascending ? "↑" : "↓")}</th>
						<th onClick={() => sortLeaderboard("average")}>Average{sortColumn === "average" && (ascending ? "↑" : "↓")}</th>
						<th onClick={() => sortLeaderboard("strikeRate")}>Strike Rate{sortColumn==="strikeRate" && (ascending ? "↑" : "↓")}</th>
						<th onClick={() => sortLeaderboard("runs")}>Runs{sortColumn === "runs" && (ascending ? "↑" : "↓")}</th>
						<th onClick={() => sortLeaderboard("balls")}>Balls{sortColumn === "balls" && (ascending ? "↑" : "↓")}</th>
						<th onClick={() => sortLeaderboard("best")}>Best{sortColumn === "best" && (ascending ? "↑" : "↓")}</th>
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
							<td>{e.statLine.innings - e.statLine.dismissals}</td>
							<td>{e.statLine.average.toFixed(2)}</td>
							<td>{e.statLine.strikeRate.toFixed(0)}</td>
							<td>{e.statLine.runs}</td>
							<td>{e.statLine.ballsFaced}</td>
							<td>{getShortBattingPerformanceSummary(e.statLine.best)}</td>
							<td>{e.statLine.fours}</td>
							<td>{e.statLine.sixes}</td>
						</tr>
					))}
				</tbody>
			</table>
		</div>
	)

}

export default BattingLeaderboard
