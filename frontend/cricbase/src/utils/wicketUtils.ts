import { TeamSummary, WicketSummary } from "../types/Match";
import { getPlayerOnTeam } from "./playerUtils";

export function getWicketString(bowlingTeam: TeamSummary, wicket: WicketSummary): string {
	if(wicket == null) {
		return "not out";
	}
	return "out";
}
