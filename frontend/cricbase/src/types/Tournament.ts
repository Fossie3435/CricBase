import { MatchSummary } from "./Match";

export interface Tournament {
	id: number;
	name: string;
	editions: TournamentEdition[]
}

export interface TournamentEdition{
	id: number;
	name: string;
	start: Date;
	end: Date;
	edition: number;
	season: string;
}
export interface RosterPointsLeaderboardEntry {
	id: number;
	name: string;
	tournamentEditionId: number;
	points: number;
}

export interface DetailedTournamentEdition {
	id: number;
	name: string;
	start: Date;
	end: Date;
	edition: number;
	season: string; 
	matches: MatchSummary[];
	pointsTable: RosterPointsLeaderboardEntry[];
}
