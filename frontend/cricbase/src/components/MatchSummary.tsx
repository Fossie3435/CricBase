import { Link } from "react-router-dom";
import { MatchSummary, getResultString, getScoreString } from "../types/Match";
import "./TournamentEdition.css";

interface MatchSummaryProps {
	match: MatchSummary;
}

function MatchSummary( { match } : MatchSummaryProps) {
	return (
		<Link className="matchSummary" to={`/matches/${match.id}`}>
			<div className="matchInfoBar">
				<div className="matchNumber">
					<h4>Match #{match.matchNumber}</h4>
				</div>
				<div className="matchDate">
					<h4>{match.date.toString()}</h4>
				</div>
			</div>

				{match.innings.map((inning) => (
			<h3 key={"inning:"+ match.id + inning.teamName}>
				{inning.teamName} 
				<span className={match.result.winner === inning.teamName 
					? "winningScore"
					: "losingScore"}>
					{getScoreString(inning)}
				</span>
			</h3>
			))}
			<div className="bottomInfoBar">
				<h5 className="groundInfo">{match.ground.name}</h5>
				<h5 className="matchResult">{getResultString(match.result)}</h5>
			</div>
		</Link> 	
	)
}

export default MatchSummary
