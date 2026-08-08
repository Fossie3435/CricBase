/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Mappers;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import uk.org.cricbase.DTOs.FallOfWicketSummary;

/**
 *
 * @author Benjamin
 */
@Mapper
public interface FallOfWicketMapper {
    @Results(id = "fallOfWicket", value = {
        @Result(property = "delivery", column = "delivery"),
        @Result(property = "total", column = "total"),
        @Result(property = "wicket", column = "wicket"),
        @Result(property = "batterId", column = "batter_id")
    })
    @Select("""
            SELECT
            delivery,
            total,
            wicket,
            batter_id
            FROM 
            fall_of_wickets
            WHERE inning_id = #{inningId}
            """)
    List<FallOfWicketSummary> findFallOfWicketSummariesByInningId(long inningId);
    
}
