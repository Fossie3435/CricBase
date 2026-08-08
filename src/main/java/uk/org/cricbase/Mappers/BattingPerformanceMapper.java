/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Mappers;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import uk.org.cricbase.DTOs.BattingPerformanceSummary;

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
        @Result(property = "wicket", column = "id", one=@One(select = "uk.org.cricbase.Mappers.WicketMapper.findWicketSummaryByBattingPerformanceId"))
    })
    @Select("""
            SELECT
            id,
            balls_faced,
            batting_position,
            fours,
            runs,
            sixes,
            batter_id
            FROM batting_performances
            WHERE inning_id = #{inningId}
            """)
    List<BattingPerformanceSummary> findBattingPerformancesByInningId(long inningId);
    
}
