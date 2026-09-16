import { Player } from './Player'

export interface Roster {
	id: number;
	name: string;
	players: Player[];
}
