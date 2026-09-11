/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import uk.org.cricbase.DTOs.WicketSummary;

/**
 *
 */
@Mapper
public interface WicketMapper {
	@ResultMap("wicketSummary")
	@Select("""
		SELECT 
			ba.name AS batter_name,
			bo.name AS bowler_name,
			w.dismissal_type AS dismissal_type,
			f.name AS fielder_name,
			wf.ordinal AS fielder_ordinal,
			wf.is_wicketkeeper AS fielder_is_wicketkeeper,
			wf.is_substitute AS fielder_is_substitute
		FROM wickets w
		JOIN players ba ON w.batter_id = ba.id
		JOIN players bo ON w.bowler_id = bo.id
		LEFT JOIN wicket_fielders wf ON w.id = wf.wicket_id
		JOIN players f ON wf.player_id = f.id
		WHERE w.id = #{id}
		ORDER BY ordinal ASC
	""")
	WicketSummary findWicketSummaryById(long id);

	@ResultMap("wicketSummary")
    @Select("""
            SELECT 
			ba.name AS batter_name,
			bo.name AS bowler_name,
			w.dismissal_type AS dismissal_type,
			f.name AS fielder_name,
			wf.ordinal AS fielder_ordinal,
			wf.is_wicketkeeper AS fielder_is_wicketkeeper,
			wf.is_substitute AS fielder_is_substitute
			FROM wickets w
			JOIN players ba ON w.batter_id = ba.id
			JOIN players bo ON w.bowler_id = bo.id
			LEFT JOIN wicket_fielders wf ON w.id = wf.wicket_id
			JOIN players f ON wf.player_id = f.id
            WHERE w.batting_performance_id = #{battingPerformanceId}           
			ORDER BY ordinal ASC
            """)
    WicketSummary findWicketSummaryByBattingPerformanceId(long battingPerformanceId);
    
    @ResultMap("wicketSummary")
    @Select("""
            SELECT 
			ba.name AS batter_name,
			bo.name AS bowler_name,
			w.dismissal_type AS dismissal_type,
			f.name AS fielder_name,
			wf.ordinal AS fielder_ordinal,
			wf.is_wicketkeeper AS fielder_is_wicketkeeper,
			wf.is_substitute AS fielder_is_substitute
			FROM wickets w
			JOIN players ba ON w.batter_id = ba.id
			JOIN players bo ON w.bowler_id = bo.id
			LEFT JOIN wicket_fielders wf ON w.id = wf.wicket_id
			JOIN players f ON wf.player_id = f.id
            WHERE w.bowling_performance_id = #{bowlingPerformanceId}
			ORDER BY ordinal ASC
	""")
    List<WicketSummary> findWicketSummariesByBowlingPerformanceId(long bowlingPerformanceId);
    
}
