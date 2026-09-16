import {TournamentEdition } from "./Tournament"

export interface CombinedStats {
	battingStats: BattingStatline[]
	bowlingStats: BowlingStatline[]
}

export interface BattingStatline {
	tournament: TournamentEdition
	matches: number;
	innings: number;
	runs: number;
	ballsFaced: number;
	fours: number;
	sixes: number;
	dismissals: number;
}

export interface BowlingStatline {
	tournament: TournamentEdition
	matches: number;
	innings: number;
	ballsBowled: number;
	runsConceded: number;
	wickets: number;
	maidens: number;
	foursConceded: number;
	wides: number;
	noBalls: number;
}
