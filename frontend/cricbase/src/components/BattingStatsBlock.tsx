import { BattingStatline } from "../types/Statline";
import "./StatsTable.css";
import { getShortBattingPerformanceSummary } from '../utils/battingPerformance';

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
					<th>Season</th>
					<th>Matches</th>
					<th>Innings</th>
					<th>Not Outs</th>
					<th>Average</th>
					<th>Strike Rate</th>
					<th>Runs</th>
					<th>Balls</th>
					<th>Best</th>
					<th>4s</th>
					<th>6s</th>
				</tr>
			</thead>

			<tbody>
				{battingStats.map((stat) => (
					<tr key={stat.tournament.id}>
						<td>{stat.tournament.name}</td>
						<td>{stat.tournament.season}</td>
						<td>{stat.matches}</td>
						<td>{stat.innings}</td>
						<td>{stat.innings - stat.dismissals}</td>
						<td>{stat.average.toFixed(2)}</td>
						<td>{stat.strikeRate.toFixed(0)}</td>
						<td>{stat.runs}</td>
						<td>{stat.ballsFaced}</td>
						<td>{getShortBattingPerformanceSummary(stat.best)}</td>
						<td>{stat.fours}</td>
						<td>{stat.sixes}</td>
					</tr>
				))}
			</tbody>
		</table>
	</div>
	)
}

export default BattingStatsBlock
