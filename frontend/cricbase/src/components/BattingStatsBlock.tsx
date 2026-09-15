import { BattingStatline } from "../types/Statline";
import "./StatsTable.css";

interface BattingStatProps {
	battingStats: BattingStatline[]
}

function BattingStatsBlock( {battingStats} : BattingStatProps) {
	if(battingStats == null || battingStats.length == 0) {
		return <></>;
	}
	
	return (
	<div className="BattingStatsBlock">
		<h2>Batting</h2>

		<table className="stats-table">
			<thead>
				<tr>
					<th>Tournament</th>
					<th>Matches</th>
					<th>Innings</th>
					<th>Runs</th>
					<th>Balls</th>
					<th>4s</th>
					<th>6s</th>
					<th>Dismissals</th>
				</tr>
			</thead>

			<tbody>
				{battingStats.map((stat) => (
					<tr key={stat.tournament.id}>
						<td>{stat.tournament.name}</td>
						<td>{stat.matches}</td>
						<td>{stat.innings}</td>
						<td>{stat.runs}</td>
						<td>{stat.ballsFaced}</td>
						<td>{stat.fours}</td>
						<td>{stat.sixes}</td>
						<td>{stat.dismissals}</td>
					</tr>
				))}
			</tbody>
		</table>
	</div>
	)
}

export default BattingStatsBlock
