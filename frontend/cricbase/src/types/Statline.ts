import {Tournament } from "./Tournament"

export interface CombinedStats {
	battingStats: BattingStatline[]
	bowlingStats: BowlingStatline[]
}

export interface BattingStatline {
	tournament: Tournament
	matches: number;
	innings: number;
	runs: number;
	ballsFaced: number;
	fours: number;
	sixes: number;
	dismissals: number;
}

export interface BowlingStatline {
	tournament: Tournament
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
