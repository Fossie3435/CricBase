import { MatchSummary, getResultString } from "../types/Match";
import "./TournamentEdition.css";

interface MatchSummaryProps {
	match: MatchSummary;
}

function MatchSummary( { match } : MatchSummaryProps) {
	if(match == null) {
		return <p>Invalid Match</p>
	}	
	return (
		<div className="matchSummary">
		{match.innings.map((inning) => (
			<h3>{inning.teamName}: {inning.runs}/{inning.wickets}</h3>
		))}
		<h5>{getResultString(match.result)}</h5>
		</div>
	)
}

export default MatchSummary
