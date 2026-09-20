/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import uk.org.cricbase.DTOs.BattingPerformanceSummary;
import uk.org.cricbase.Models.BattingPerformance;

/**
 *
 * @author Benjamin
 */
@Mapper
public interface BattingPerformanceMapper {
    @Results(id = "battingPerformanceSummary", value = {
        @Result(property = "batterId", column = "batter_id"),
        @Result(property = "battingPosition", column = "batting_position"),
        @Result(property = "runs", column = "runs"),
        @Result(property = "ballsFaced", column = "balls_faced"),
        @Result(property = "fours", column = "fours"),
        @Result(property = "sixes", column = "sixes"),
		@Result(property = "isDismissed", column = "is_dismissed"),
        @Result(property = "wicket", column = "id", one=@One(select = "uk.org.cricbase.Mappers.WicketMapper.findWicketSummaryByBattingPerformanceId"))
   })
    @Select("""
            SELECT
            bp.id,
            bp.balls_faced,
            bp.batting_position,
            bp.fours,
            bp.runs,
            bp.sixes,
            bp.batter_id,
			bp.is_dismissed
            FROM batting_performances bp
            WHERE bp.inning_id = #{inningId}
            """)
    List<BattingPerformanceSummary> findBattingPerformancesByInningId(long inningId);
	
	@ResultMap("battingPerformanceSummary")
	@Select("""
		SELECT 
		    bp.id,
            bp.balls_faced,
            bp.batting_position,
            bp.fours,
            bp.runs,
            bp.sixes,
            bp.batter_id,
			bp.is_dismissed
		FROM batting_performances bp
		JOIN innings i ON bp.inning_id = i.id 
		JOIN matches m ON i.match_id = m.id
		WHERE batter_id = #{pId}
		AND m.tournament_id = #{tId}
	""")
    List<BattingPerformanceSummary> getBattingPerformancesByPlayerIdAndTournamentEditionId(@Param("tId") long tournamentEditionId, @Param("pId") String playerId);
	
	@Select("""
		SELECT 
			bp.id AS id 
		FROM batting_performances bp
		JOIN innings i ON bp.inning_id = i.id
		JOIN matches m ON i.match_id = m.id
		WHERE m.tournament_id = #{tId} AND bp.batter_id = #{pId}
		ORDER BY bp.runs DESC, bp.is_dismissed ASC
		LIMIT 1
	""")
    BattingPerformance getBestBattingPerformanceByTournamentEditionIdAndPlayerId(@Param("tId") long tournamentEditionId, @Param("pId") String playerId);


    
}
