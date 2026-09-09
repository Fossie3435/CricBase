/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import uk.org.cricbase.DTOs.DetailedTournamentEditionSummary;
import uk.org.cricbase.DTOs.TournamentEditionSummary;
import uk.org.cricbase.DTOs.TournamentSummary;
import uk.org.cricbase.Models.Tournament;
import uk.org.cricbase.Models.TournamentEdition;

/**
 *
 * 
 */
@Mapper
public interface TournamentMapper {
    @Insert("""
            INSERT INTO tournaments
                (name)
            VALUES 
                (#{name})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertTournament(Tournament t);
    
    @Insert("""
            INSERT INTO tournament_editions
                (name, dates, tournament_id, edition)
            VALUES
                (#{e.name}, daterange(#{e.start}::date, #{e.end}::date, '[)') , #{e.tournamentId}, #{e.edition})
            
            """)
    @Options(useGeneratedKeys = true, keyProperty = "e.id")
    void insertEdition(@Param("e") TournamentEdition e);

	@Select("""
		SELECT 
			name
		FROM
			tournaments
		WHERE id = #{id}
	""")
	String findTournamentNameById(long id);

	@Select("""
		SELECT 
			id, 
			lower(dates) AS start,
			upper(dates) AS end
		FROM 
			tournament_editions
		WHERE tournament_id = #{id}
	""")
	List<TournamentEdition> findTournamentEditionsByTournamentId(long id);

	@Select("""
		SELECT 
			id, 
			lower(dates) AS start,
			upper(dates) AS end,
			name,
			edition
		FROM 
			tournament_editions
		WHERE tournament_id = #{id}
	""")
	List<TournamentEditionSummary> findTournamentEditionSummariesByTournamentId(long id);
	
	@Results(id = "tournamentSummaryResult", value = {
		@Result(property = "id", column = "id", id = true),
        @Result(property = "name", column = "name"),
		@Result(property = "editions", column = "id", many=@Many(select = "uk.org.cricbase.Mappers.TournamentMapper.findTournamentEditionSummariesByTournamentId"))
	})
	@Select("""
		SELECT 
			id,
			name
		FROM
			tournaments
		WHERE id = #{id}
	""")
	TournamentSummary findTournamentSummaryById(long id);
	
	@Results(id = "detailedTournamentEditionSummary", value = {
		@Result(property = "id", column = "id", id = true),
		@Result(property = "name", column = "name"),
		@Result(property = "start", column = "start"),
		@Result(property = "end", column = "end"),
		@Result(property = "edition", column = "edition"),
		@Result(property = "tournamentId", column = "tournament_id"),
		@Result(property = "matches", column = "id", many=@Many(select = "uk.org.cricbase.Mappers.MatchMapper.findMatchSummariesByTournamentEditionId"))
	})
	@Select("""
		SELECT 
			id,
			name,
			lower(dates) AS start,
			upper(dates) AS end,
			edition,
			tournament_id
		FROM tournament_editions
		WHERE id= #{id}
	""")
	DetailedTournamentEditionSummary findDetailedTournamentEditionSummary(long id);

	
}
