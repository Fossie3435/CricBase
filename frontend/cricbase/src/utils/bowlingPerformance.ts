import { BowlingPerformanceSummary } from "../types/Match";

export function getShortBowlingPeformanceSummary(bp: BowlingPerformanceSummary): string {
	return (bp.wicketsTaken + "/" + bp.runsConceded);
}
