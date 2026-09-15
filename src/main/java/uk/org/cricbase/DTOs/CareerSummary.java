package uk.org.cricbase.DTOs;

import java.util.List;

public record CareerSummary (
	List<BowlingStatsSummary> bowlingStats,
	List<BattingStatsSummary> battingStats
) {}
