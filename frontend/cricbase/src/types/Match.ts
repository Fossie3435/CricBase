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

export interface Toss {
	winner: string;
	decision: string;
}

export interface DetailedMatchSummary {
	matchSummary: MatchSummary;
	innings: DetailedInningSummary[];
	teams: TeamSummary[];
}

export interface DetailedInningSummary {
	battingTeam: string;
	bowlingTeam: string;
	id: number;
    total: number;
    wickets: number;
    runs: number;
    byes: number;
    legbyes: number;
    wides: number;
    noballs: number;
    penaltyRuns: number;
    battingScorecard: BattingPerformanceSummary[];
    bowlingScorecard: BowlingPerformanceSummary[];
    fallOfWickets: FallOfWicketSummary[];
}

export interface BattingPerformanceSummary {
	batterId : string;
    battingPosition: number;
    runs: number;
    ballsFaced: number;
    fours: number;
    sixes: number;
	isDismissed: boolean;
    wicket: WicketSummary;
}

export interface WicketSummary {
    batterOut: string;
    bowler: string;
    dismissalType: string;
	fielders: Fielder[];
}

export interface Fielder {
	fielder: string;
	isWicketkeeper: boolean;
	isSubstitute: boolean;
}

export interface BowlingPerformanceSummary {
    bowlerId: string;
    bowlingPosition: number;
    maidens: number;
    ballsBowled: number;
    runsConceded: number;
    wicketsTaken: number;
    dots: number;
    foursConceded: number;
    sixesConceded: number;
    wides: number;
    noballs: number;
 
}

export interface FallOfWicketSummary {
    delivery: string;
    total: number;
    wicket: number;
    batterId: string;
}

export interface TeamSummary {
	id: number;
	name: string;
	tricode: string;
	players: Player[];
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

export function getScoreString(inning: InningSummary) {
	if(inning.wickets == 10) {
		return (inning.runs + " ao");
	}
	return (inning.runs + "/" + inning.wickets);
}

