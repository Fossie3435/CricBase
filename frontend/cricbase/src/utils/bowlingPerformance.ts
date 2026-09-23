import { BowlingPerformanceSummary } from "../types/Match";

export function getShortBowlingPeformanceSummary(bp: BowlingPerformanceSummary): string {
	if(bp == null || bp.wicketsTaken == null || bp.runsConceded == null) {
		return " - ";
	}
	return (bp.wicketsTaken + "/" + bp.runsConceded);
}
