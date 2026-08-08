/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Mappers;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import uk.org.cricbase.DTOs.WicketSummary;

/**
 *
 * @author Benjamin
 */
@Mapper
public interface WicketMapper {
    @Results(id = "wicketSummary", value = {
        @Result(property = "batterOut", column = "batter_id"),
        @Result(property = "bowler", column = "bowler_id"),
        @Result(property = "dismissalType", column = "dismissal_type")
    })
    @Select("""
            SELECT 
            dismissal_type,
            bowler_id,
            batter_id
            FROM 
            wickets
            WHERE batting_performance_id = #{battingPerformanceId}           
            """)
    WicketSummary findWicketSummaryByBattingPerformanceId(long battingPerformanceId);
    
    @ResultMap("wicketSummary")
    @Select("""
            SELECT 
            dismissal_type,
            bowler_id,
            batter_id,
            FROM
            wickets
            WHERE bowling_performance_id = #{bowlingPerformanceId}
            """)
    List<WicketSummary> findWicketSummariesByBowlingPerformanceId(long bowlingPerformanceId);
    
}
