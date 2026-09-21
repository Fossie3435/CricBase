import { Player } from "./Player";

export interface StatLeaderboard {
	name: string;
	statName: string;
	statUnit: string;
	entries: StatEntry[];
	decimalPlaces: number;
	statType: string;
}

export interface StatEntry {
	entry: number;
	player: Player;
	stat: number;
}
