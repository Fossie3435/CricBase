/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Mappers;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
                (#{e.name}, daterange(#{e.start}::date, #{e.end}::date, '[)') , #{t}, #{e.edition})
            
            """)
    @Options(useGeneratedKeys = true, keyProperty = "e.id")
    void insertEdition(@Param("e") TournamentEdition e, @Param("t") long t);

	@Select("""
		SELECT 
			name
		FROM
			tournaments
		WHERE id = #{id}
	""")
	String findTournamentNameById(long id);

	@Results(id = "tournamentEdition", value = {
    	@Result(property = "id", column = "id", id = true),
    	@Result(property = "name", column = "name"),
    	@Result(property = "edition", column = "edition"),
    	@Result(property = "start", column = "start"),
    	@Result(property = "end", column = "end"),
    	@Result(property = "tournamentId", column = "tournament_id")
	})
	@Select("""
    	SELECT
        	id,
        	name,
        	edition,
        	lower(dates) AS start,
        	upper(dates) AS end,
        	tournament_id,
			season
    	FROM tournament_editions
    	WHERE tournament_id = #{id}
		ORDER BY start
    """)
	List<TournamentEdition> findTournamentEditionsByTournamentId(long id);

	@Select("""
		SELECT 
			id, 
			lower(dates) AS start,
			upper(dates) AS end,
			name,
			edition,
			season
		FROM 
			tournament_editions
		WHERE tournament_id = #{id}
		ORDER BY start
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
		@Result(property = "season", column = "season"),
		@Result(property = "matches", column = "id", many=@Many(select = "uk.org.cricbase.Mappers.MatchMapper.findMatchSummariesByTournamentEditionId"))
	})
	@Select("""
		SELECT
			id,
			name,
			lower(dates) AS start,
			upper(dates) AS end,
			edition,
			tournament_id,
			season
		FROM tournament_editions
		WHERE id= #{id}
	""")
	DetailedTournamentEditionSummary findDetailedTournamentEditionSummary(long id);

	@ResultMap("tournamentEdition")
	@Select("""
    	SELECT
        	id,
        	name,
        	edition,
        	lower(dates) AS start,
        	upper(dates) AS end,
        	tournament_id,
			season
    	FROM tournament_editions
    	WHERE id = #{id}
    """)
    TournamentEdition findTournamentEditionById(long id);

	@Update("""
		UPDATE tournament_editions
			SET 
				dates = daterange(#{start}::date, #{end}::date, '[]')
			WHERE id = #{tId}
	""")
    void updateTournamentEditionDates(@Param("tId") long TournamentEditionId, @Param("start") LocalDate start, @Param("end") LocalDate end);

	@ResultMap("tournamentSummaryResult")
	@Select("""
		SELECT 
			id,
			name
		FROM tournaments
	""")
    List<TournamentSummary> findAllTournamentSummaries();
}
