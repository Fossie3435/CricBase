import { BowlingStatline } from "../types/Statline";
import "./StatsTable.css";
import { getShortBowlingPeformanceSummary } from '../utils/bowlingPerformance';

interface BowlingStatProps {
	bowlingStats: BowlingStatline[]
}

function BattingStatsBlock( {bowlingStats} : BowlingStatProps) {
	if(bowlingStats == null || bowlingStats.length == 0) {
		return <></>;
	}
	
	return (
	<div className="BowlingStatsBlock">
		<h2>Bowling</h2>
	<table className="stats-table">
		<thead>
			<tr>
				<th>Tournament</th>
				<th>Season</th>
				<th>Matches</th>
				<th>Innings</th>
				<th>Economy</th>
				<th>Average</th>
				<th>Wickets</th> 
				<th>Balls</th>
				<th>Runs</th>
				<th>Best</th>
				<th>Maidens</th>
				<th>4s</th>
				<th>Wides</th>
				<th>No Balls</th>
			</tr>
		</thead>

		<tbody>
			{bowlingStats.map(stat => (
				<tr key={stat.tournament.id}>
					<td>{stat.tournament.name}</td>
					<td>{stat.tournament.season}</td>
					<td>{stat.matches}</td>
					<td>{stat.innings}</td>
					<td>{stat.economyRate.toFixed(2)}</td>
					<td>{stat.average.toFixed(2)}</td>
					<td>{stat.wickets}</td>
					<td>{stat.ballsBowled}</td>
					<td>{stat.runsConceded}</td>
					<td>{getShortBowlingPeformanceSummary(stat.best)}</td>
					<td>{stat.maidens}</td>
					<td>{stat.foursConceded}</td>
					<td>{stat.wides}</td>
					<td>{stat.noBalls}</td>
				</tr>
			))}
		</tbody>
	</table>


	</div>
	)
}

export default BattingStatsBlock
