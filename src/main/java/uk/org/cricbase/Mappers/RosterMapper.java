package uk.org.cricbase.Mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import uk.org.cricbase.DTOs.RosterPointsLeaderboardEntry;
import uk.org.cricbase.DTOs.RosterSummary;
import uk.org.cricbase.DTOs.RosterTournamentEditionEntry;
import uk.org.cricbase.Models.Player;
import uk.org.cricbase.Models.Roster;

@Mapper
public interface RosterMapper {
	@Insert("""
		INSERT INTO rosters
		(name, tricode, tournament_edition_id)
		VALUES
		(#{name}, #{tricode}, #{tournament.id})
	""")
    @Options(useGeneratedKeys = true, keyProperty = "id")
	void insertRoster(Roster r);

	@ResultMap("rosterSummary")
	@Select("""
		SELECT
			r.id AS roster_id,
			r.name AS roster_name,
			r.tournament_edition_id AS tournament_edition_id,
			p.id AS player_id,
			p.nickname AS player_nickname,
			p.name AS player_name
		FROM players_rosters pr
		JOIN players p ON pr.player_id = p.id
		RIGHT JOIN rosters r ON pr.roster_id = r.id
		WHERE r.id = #{rId}
	""")
	RosterSummary findRosterSummaryById(@Param("rId") long rosterId);

	@ResultMap("rosterTournamentEditionSummary")
	@Select("""
		SELECT
			r.id AS roster_id,
			r.name AS roster_name,
			te.id AS tournament_edition_id,
			te.name AS tournament_edition_name,
			te.season AS tournament_edition_season,
			te.edition AS tournament_edition_edition,
			p.id AS player_id,
			p.nickname AS player_nickname,
			p.name AS player_name
		FROM players_rosters pr
		JOIN players p ON pr.player_id = p.id
		RIGHT JOIN rosters r ON pr.roster_id = r.id
		JOIN tournament_editions te ON r.tournament_edition_id = te.id
		ORDER BY te.id, r.id, p.id
	""")
    List<RosterTournamentEditionEntry> findAllRosterSummaries();

	@Results(id="roster", value={
		@Result(property="id", column="id"),
		@Result(property="name", column="name")
	})
	@Select("""
		SELECT 
			id,
			name,
		FROM rosters
		WHERE id = #{rId}
		AND name = #{rName}

	""")
    Roster findRosterByTournamentIdAndName(@Param("rId") long id, @Param("rName")String name);
	
	@Results(id="rosterPointsLeaderboardEntry", value={
		@Result(property="id", column="id"),
		@Result(property = "name", column = "name"),
		@Result(property = "tournamentEditionId", column = "tournament_edition_id"),
		@Result(property = "points", column = "points"),
		@Result(property = "matches", column = "matches"),
		@Result(property = "wins", column = "wins"),
		@Result(property = "losses", column = "losses"),
		@Result(property = "noResults", column = "no_results")
	})
	@Select("""
		SELECT 
			r.id AS id, 
			r.name AS name,
			r.tournament_edition_id AS tournament_edition_id,
			SUM(t.points) AS points,
			COUNT(*) as matches,
			COUNT(*) FILTER(where m.winner = t.id) as wins,
			COUNT(*) FILTER(WHERE m.winner != t.id AND m.result_type = 'RESULT') AS losses,
			COUNT(*) FILTER(WHERE m.result_type = 'NO_RESULT') AS no_results
		FROM rosters r 
		JOIN teams t ON t.roster_id = r.id
		JOIN matches m ON t.match_id = m.id 
		WHERE r.tournament_edition_id = #{tId} 
		AND m.type = 'GROUP_STAGE'
		GROUP BY r.id, r.name 
		ORDER BY SUM(t.points) DESC;
	""")
    List<RosterPointsLeaderboardEntry> findPointsTableByTournamentEditionId(@Param("tId") long tournamentEditionId);

	@Insert("""
		INSERT INTO players_rosters
			(player_id, roster_id)
		VALUES
			(#{player.id}, #{rId})
	""")
    void insertPlayerOnRoster(@Param("player") Player player,@Param("rId") long rosterId);
	
	@Insert("""
		SELECT 1
		FROM players_rosters pr
		WHERE pr.player_id = #{player.id} 
		AND pr.roster_id = #{rId}
	""")
    boolean isPlayerOnRoster(@Param("player") Player player, @Param("rId") long rosterId);

}
