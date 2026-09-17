import { Player } from "./Player";

export interface StatLeaderboard {
	name: string;
	statName: string;
	entries: StatEntry[];
	decimalPlaces: number;
}

export interface StatEntry {
	entry: number;
	player: Player;
	stat: number;
}
