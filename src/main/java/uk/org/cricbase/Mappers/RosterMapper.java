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

import uk.org.cricbase.DTOs.RosterSummary;
import uk.org.cricbase.Models.PlayerRosterContainer;
import uk.org.cricbase.Models.Roster;

@Mapper
public interface RosterMapper {

	@Insert("""
		INSERT INTO players_rosters
		(player_id, active, roster_id)
		VALUES
		(#{player.id}, daterange(#{start}::date, #{end}::date, '[]'), #{roster.id})
	""")
	void insertPlayerOnRoster(PlayerRosterContainer p);

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
	""")
    List<RosterSummary> findAllRosterSummaries();

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
}
