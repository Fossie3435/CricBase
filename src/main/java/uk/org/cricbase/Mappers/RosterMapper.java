package uk.org.cricbase.Mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
		(#{name}, {tournament.id})
	""")
	void insertRoster(Roster r);
}
