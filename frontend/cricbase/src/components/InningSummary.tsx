import { DetailedInningSummary, TeamSummary } from "../types/Match"
import { getPlayerOnTeam} from '../utils/playerUtils'
import { getWicketString} from '../utils/wicketUtils'

interface InningSummaryProps {
	inning: DetailedInningSummary;
	teams: TeamSummary[];
}


function InningSummary({inning, teams } : InningSummaryProps) {
	let battingTeam: TeamSummary | undefined;
	let bowlingTeam: TeamSummary | undefined;
	
	if(inning.battingTeam == teams.at(0)?.name) {
		battingTeam = teams.at(0);
		bowlingTeam = teams.at(1);
	} else {
		battingTeam = teams.at(1);
		bowlingTeam = teams.at(0);
	}

	if(battingTeam == undefined || bowlingTeam == undefined) {
		return <p>Error!</p>;
	}
	return (
		<div className="detailedInningSummary">
			<h1>{battingTeam.name}</h1>
			<h4>{inning.total}{inning.wickets == 10 ? (" ao"):String  ("/" + inning.wickets)}</h4>
			<table className="battingInningStats"> 
				<tr>
					<td></td>
					<td></td>
					<td>runs</td>
					<td>balls</td>
					<td>4s</td>
					<td>6s</td>
				</tr>
				{inning.battingScorecard.map((bp) => (
					<tr>
						<td>{ getPlayerOnTeam(battingTeam, bp.batterId).nickname}</td>
						<td>{ getWicketString(bowlingTeam, bp.wicket) }</td>
						<td>{bp.runs}</td>
						<td>{bp.ballsFaced}</td>
						<td>{bp.fours}</td>
						<td>{bp.sixes}</td>
					</tr>
				))}
				<tr>
					<td><strong>Extras</strong></td>
					<td>{inning.wides}w {inning.noballs}nb {inning.byes}b {inning.legbyes}lb</td>
				</tr>

			</table>
			<table className="bowlingInningStats">
				<tr>
					<td></td>
					<td>balls</td>
					<td>runs</td>
					<td>wickets</td>
					<td>dots</td>
					<td>wides</td>
					<td>no balls</td>
				</tr>
				{inning.bowlingScorecard.map((bp) => (
					<tr>
						<td>{ getPlayerOnTeam(bowlingTeam, bp.bowlerId).nickname}</td>
						<td>{ bp.ballsBowled}</td>
						<td>{ bp.runsConceded}</td>
						<td>{ bp.wicketsTaken}</td>
						<td>{ bp.dots }</td>
						<td>{ bp.wides}</td>
						<td>{ bp.noballs}</td>
					</tr>
				))}
			</table>
			
		</div>
	)
}

export default InningSummary
