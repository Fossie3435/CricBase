import { BattingPerformanceSummary } from "../types/Match";

export function getShortBattingPerformanceSummary(bp:BattingPerformanceSummary): string {
	if(bp.dismissed === false) {
		return (bp.runs + "*");
	}
	return String(bp.runs);
}
