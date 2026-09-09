/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
