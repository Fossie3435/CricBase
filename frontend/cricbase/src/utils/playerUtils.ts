import { Player} from '../types/Player';
import { TeamSummary } from '../types/Match'

export function getPlayerOnTeam(team: TeamSummary, playerId: string): Player  {
	const player = team.players.find((player) => player.id === playerId);

	if (player === undefined) {
		throw new Error(`Player ${playerId} not found on team ${team.name}`);
	}

	return player;
}
