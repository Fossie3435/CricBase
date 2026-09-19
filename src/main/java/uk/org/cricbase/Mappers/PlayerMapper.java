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
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import uk.org.cricbase.Models.Player;

/**
 *
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
            
    """)
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

	@ResultMap("playerResult")
	@Select("""
		SELECT * FROM players WHERE name ILIKE '%' || #{search} || '%' AND nickname IS NOT NULL
		UNION
		SELECT * FROM Players WHERE nickname ILIKE '%' || #{search} || '%' 
	""")
	List<Player> searchPlayers(String search);
	
	@ResultMap("playerResult")
	@Select("""
		SELECT DISTINCT players.* FROM players_teams JOIN players ON players_teams.player_id = players.id WHERE players.nickname IS NULL LIMIT 50
	""")
    List<Player> findActivePlayersWithoutNickname();
	
	@Update("""
		UPDATE players
		 SET 
		 	nickname = #{nickname}
		 WHERE id = #{id}
	""")
    void updateNickname(Player player);

	@ResultMap("playerResult")
	@Select("""
		SELECT DISTINCT p.*
		FROM players_teams pt 
		JOIN players p ON pt.player_id = p.id
		JOIN teams t ON pt.team_id = t.id
		JOIN matches m ON t.match_id = m.id
		WHERE m.tournament_id = #{tId}
	""")
    List<Player> findPlayersByTournamentEdition(@Param("tId") long tournamentEditionId);

}
