import { TeamSummary } from "../types/Match";

interface TeamSummaryProps {
	team: TeamSummary;
}

function TeamSummaryComponent({team}: TeamSummaryProps) {
	return (
		<div className="TeamSummary">
			<h3>{team.name}</h3>
			{team.players.map((player) => (
				<h4>{player.nickname}</h4>
			))}
		</div>
	)
}

export default TeamSummaryComponent
