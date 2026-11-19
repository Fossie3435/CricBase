import { TeamSummary, WicketSummary } from "../types/Match";
import { getPlayerOnTeam } from "./playerUtils";

export function getWicketString(bowlingTeam: TeamSummary, wicket: WicketSummary): string {
	if(wicket == null) {
		return "not out";
	}
	switch (wicket.dismissalType) {
		case "caught":
			console.log(wicket.fielders)
			if(wicket.bowlerId == wicket.fielders[0]?.fielder) {
				console.log("caught and bowled")
				return "c&b " + getPlayerOnTeam(bowlingTeam, wicket.bowlerId)
			} else {
				console.log(wicket.fielders)
				console.log("Bowler ID:", wicket.bowlerId);
				console.log("Fielder ID:", wicket.fielders[0]?.fielder);
				var fielder = wicket.fielders[0]?.substitute ? "sub": getPlayerOnTeam(bowlingTeam, wicket.fielders[0]?.fielder).nickname
				var bowler = getPlayerOnTeam(bowlingTeam, wicket.bowlerId)
				
				return "c " + fielder+ " b " + bowler.nickname
			}
		case "bowled":
			var bowler	= getPlayerOnTeam(bowlingTeam, wicket.bowlerId)
			return "b " + bowler.nickname
		case "lbw":
			var bowler	= getPlayerOnTeam(bowlingTeam, wicket.bowlerId)
			return "lbw " + bowler.nickname
		case "stumped":
			var fielder = wicket.fielders[0]?.substitute ? "sub": getPlayerOnTeam(bowlingTeam, wicket.fielders[0]?.fielder).nickname;
			var bowler = getPlayerOnTeam(bowlingTeam, wicket.bowlerId)
			return "st " + fielder + " b " + bowler.nickname
		case "run out":
			console.log(wicket.fielders)
			var fielders = wicket.fielders.map((fielder) => (fielder.substitute ? "sub": getPlayerOnTeam(bowlingTeam, fielder.fielder).nickname));
			var fieldersString = fielders[0];
			for (let i = 1; i < fielders.length; i++) {
				fieldersString+= "/"
				fieldersString+=fielders[i]	
			}
			return "run out (" + fieldersString + ")";

		case "hit wicket":
			return "hit wicket b " + getPlayerOnTeam(bowlingTeam, wicket.bowlerId).nickname;
		case "retired out":
			return "retired out";
		case "retired hurt":
			return "retired hurt";
		default:
			return "out"
	}
}
