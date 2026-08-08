/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Mappers;

import java.util.List;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import uk.org.cricbase.DTOs.BowlingPerformanceSummary;

/**
 *
 * @author Benjamin
 */
@Mapper
public interface BowlingPerformanceMapper {
    
    @Results(id = "bowlingPerformanceSummary", value = {
        @Result(property = "bowlerId", column = "bowler_id"),
        @Result(property = "bowlingPosition", column = "bowling_position"),
        @Result(property = "maidens", column = "maidens"),
        @Result(property = "ballsBowled", column = "balls_bowled"),
        @Result(property = "runsConceded", column = "runs_conceded"),
        @Result(property = "foursConceded", column = "fours_conceded"),
        @Result(property = "sixesConceded", column = "sixes_conceded"),
        @Result(property = "dots", column = "dots"),
        @Result(property = "wides", column = "wides"),
        @Result(property = "noballs", column = "no_balls"),
        @Result(property = "wicketsTaken", column = "wicket_count")        
    })
    @Select("""
            SELECT
            id,
            balls_bowled,
            bowling_position,
            fours_conceded,
            runs_conceded,
            sixes_conceded,
            bowler_id,
            maidens,
            no_balls,
            wides,
            wicket_count
            FROM bowling_performances
            WHERE inning_id = #{inningId}
            """)
    List<BowlingPerformanceSummary> findBowlingPerformancesByInningId();
        
}
