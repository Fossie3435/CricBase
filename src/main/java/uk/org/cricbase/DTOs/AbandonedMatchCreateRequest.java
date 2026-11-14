package uk.org.cricbase.DTOs;

import java.time.LocalDate;

public record AbandonedMatchCreateRequest(
	int ballsPerOver,
	String gender,
	String matchFormat,
	String season,
	String team_type,
	int overs,
	long groundId,
	String matchType,
	LocalDate date,
	String homeTeam,
	String awayTeam,
	long tournamentId,
	int matchNumber
) {}
