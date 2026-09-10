package uk.org.cricbase.DTOs;

import java.time.LocalDate;

/**
 *
 */
public record TournamentEditionCreateRequest(
    Long tournamentId,
    Integer editionNumber,
    LocalDate start,
    LocalDate end,
    String name
) {}
