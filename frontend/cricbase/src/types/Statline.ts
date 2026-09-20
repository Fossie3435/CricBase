import { Player } from "./Player";
import {TournamentEdition } from "./Tournament"

export interface CombinedStats {
	battingStats: BattingStatline[]
	bowlingStats: BowlingStatline[]
}

export interface BattingLeaderboardEntry {
	player: Player;
	statLine: BattingStatline;
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

export interface BowlingLeaderboardEntry {
	player: Player;
	statLine: BowlingStatline;
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
	sixesConceded: number;
	wides: number;
	noBalls: number;
}
