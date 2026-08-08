/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Mappers;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import uk.org.cricbase.Models.Player;

/**
 *
 * @author Benjamin
 */
@Mapper
public interface PlayerMapper {
    @Select("SELECT * FROM players WHERE id = #{id}")
    @Results(id = "playerResult", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "name", column = "name"),
        @Result(property = "uniqueName", column = "unique_name"),
        @Result(property = "cricInfoId", column = "cricinfo_id")
    })
    Player findById(String id);
    
    @Select("SELECT * FROM players_teams RIGHT JOIN players ON players_teams.player_id = players.id WHERE players_teams.team_id = #{teamId}")
    @ResultMap("playerResult")
    List<Player> findPlayersByTeamId(long teamId);
    
    @Insert("""
            INSERT INTO players 
            (cricinfo_id, name, unique_name)
            VALUES
            (#{cricInfoId}, #{name}, #{uniqueName})
            
    )""")
    @Options (useGeneratedKeys = false)
    void insert(Player player);
    
    
    @Update("""
            UPDATE players
            SET
                cricinfo_id = #{cricInfoId},
                name = #{name},
                nickname = #{nickname},
                unique_name = #{uniqueName}
            WHERE id = #{id}
            """)
    void update(Player player);
    
    @Delete("DELETE FROM players WHERE id=#{id}")
    void delete(String id);
}
