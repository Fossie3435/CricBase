package uk.org.cricbase.Mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import uk.org.cricbase.DTOs.RosterSummary;
import uk.org.cricbase.Models.PlayerRosterContainer;
import uk.org.cricbase.Models.Roster;

@Mapper
public interface RosterMapper {

	@Insert("""
		INSERT INTO players_rosters
		(player_id, active)
		VALUES
		(#{player.id}, daterange(#{start}::date, #{end}::date, '[)'))
	""")
	void insertPlayerOnRoster(PlayerRosterContainer p);

	@Insert("""
		INSERT INTO rosters
		(name, tournament_edition_id)
		VALUES
		(#{name}, #{tournament.id})
	""")
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
		FROM rosters
		WHERE id = #{rId}
	""")
	RosterSummary findRosterSummaryById(@Param("rId") long rosterId);
	
	

}
