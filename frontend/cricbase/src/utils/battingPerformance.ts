import { BattingPerformanceSummary } from "../types/Match";

export function getShortBattingPerformanceSummary(bp:BattingPerformanceSummary): string {
	if(bp == null || bp.dismissed == null || bp.runs == null) {
		return " - ";
	}
	if(bp.dismissed === false) {
		return (bp.runs + "*");
	}
	return String(bp.runs);
}
