import { TournamentEdition } from "./Tournament";
import { Player } from "./Player"

export interface MatchSummary {
    id: number;
    season: string;
	gender: string;
	ballsPerOver: number;
    matchNumber: number;
    format: string;
    teamType: string;
    overs: number;
	date: Date;

    innings: InningSummary[];
    ground: Ground;
    tournament: TournamentEdition;
	result: Result;
	potm: Player;
	toss: Toss;
}

export interface InningSummary {
	teamName: string;
	tricode: string;
	runs: number;
	wickets: number;
}

export interface Ground {
	id: number;
	name: string;
	city: string;
}

export interface Result {
	winner: string;
	type: string;
	runsMargin: number;
	wicketsMargin: number;
	innings: number;
}

export function getResultString(result: Result): string {
	if(result.type == "RESULT") {
		var returnString: string = result.winner + " won by ";

		if(result.runsMargin != null) {
			returnString += result.runsMargin + " runs";
		} else if(result.wicketsMargin != null) {
			returnString += result.wicketsMargin + " wickets";
		}

		return returnString;
	}
	return result.type;
}

export interface Toss {
	winner: string;
	decision: string;
}

export interface DetailedMatchSummary {


}

