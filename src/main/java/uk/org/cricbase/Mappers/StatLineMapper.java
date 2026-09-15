package uk.org.cricbase.Mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import uk.org.cricbase.DTOs.BattingStatsSummary;
import uk.org.cricbase.DTOs.BowlingStatsSummary;
import uk.org.cricbase.Models.BattingStatLine;
import uk.org.cricbase.Models.BowlingStatLine;

@Mapper
public interface StatLineMapper {
	@Select("""
		SELECT COUNT(*) 
		FROM Players_teams pt 
		JOIN teams t ON pt.team_id = t.id 
		JOIN matches m ON t.match_id = m.id
		WHERE m.tournament_id = #{tId} 
		AND player_id = #{pId}
	""")
    int getMatchCount(@Param("tId") long tournamentEditionId, @Param("pId") String playerId);

	@Insert("""
		INSERT INTO bowling_stats
			(bowler_id, tournament_edition_id, matches, innings, balls_bowled, runs_conceded, wickets, maidens, fours_conceded, sixes_conceded, wides, no_balls)
		VALUES
			(#{bowlerId}, #{tournamentEditionId}, #{matches}, #{innings}, #{ballsBowled}, #{runsConceded}, #{wickets}, #{maidens}, #{foursConceded}, #{sixesConceded}, #{wides}, #{noBalls})
		ON CONFLICT (bowler_id, tournament_edition_id) DO UPDATE SET
			matches = EXCLUDED.matches,
			innings = EXCLUDED.innings,
			balls_bowled = EXCLUDED.balls_bowled,
			runs_conceded = EXCLUDED.runs_conceded,
			wickets = EXCLUDED.wickets,
			maidens = EXCLUDED.maidens,
			fours_conceded = EXCLUDED.fours_conceded,
			sixes_conceded = EXCLUDED.sixes_conceded,
			wides = EXCLUDED.wides,
			no_balls = EXCLUDED.no_balls
	""")
    void insertOrUpdateBowlingStats(BowlingStatLine bowlingStatLine);
	
	@Insert("""
		INSERT INTO batting_stats
			(batter_id, tournament_edition_id, runs, balls_faced, matches, innings, fours, sixes, dismissals)
		VALUES
			(#{batterId}, #{tournamentEditionId}, #{runsScored}, #{ballsFaced}, #{matches}, #{innings}, #{fours}, #{sixes}, #{dismissals})
		ON CONFLICT (batter_id, tournament_edition_id) DO UPDATE SET
			matches = EXCLUDED.matches,
			innings = EXCLUDED.innings,
			runs = EXCLUDED.runs,
			balls_faced = EXCLUDED.balls_faced,
			fours = EXCLUDED.fours,
			sixes = EXCLUDED.sixes,
			dismissals = EXCLUDED.dismissals
	""")
    void insertOrUpdateBattingStats(BattingStatLine battingStatLine);
	
	@Select("""
    SELECT 
        bs.bowler_id,
        bs.tournament_edition_id,
        bs.matches,
        bs.innings,
        bs.balls_bowled,
        bs.runs_conceded,
        bs.wickets,
        bs.maidens,
        bs.fours_conceded,
		bs.sixes_conceded,
        bs.wides,
        bs.no_balls,
        te.id AS te_id,
        te.name AS te_name,
        lower(te.dates) AS te_start,
        upper(te.dates) AS te_end,
        te.edition AS te_edition,
		te.season AS te_season
    FROM bowling_stats bs
    JOIN tournament_editions te
        ON bs.tournament_edition_id = te.id
    WHERE bs.bowler_id = #{playerId}
    """)
@Results(id = "bowlingStatsSummary", value = {
    @Result(property = "matches", column = "matches"),
    @Result(property = "innings", column = "innings"),
    @Result(property = "ballsBowled", column = "balls_bowled"),
    @Result(property = "runsConceded", column = "runs_conceded"),
    @Result(property = "wickets", column = "wickets"),
    @Result(property = "maidens", column = "maidens"),
    @Result(property = "foursConceded", column = "fours_conceded"),
	@Result(property = "sixesConceded", column = "sixes_conceded"),
    @Result(property = "wides", column = "wides"),
    @Result(property = "noBalls", column = "no_balls"),

    @Result(property = "tournament.id", column = "te_id"),
    @Result(property = "tournament.name", column = "te_name"),
    @Result(property = "tournament.start", column = "te_start"),
    @Result(property = "tournament.end", column = "te_end"),
    @Result(property = "tournament.edition", column = "te_edition"),
	@Result(property = "tournament.season", column = "te_season")
})
    List<BowlingStatsSummary> getBowlingStatsSummary(@Param("playerId") String playerId);

	@Select("""
    SELECT
        bs.runs,
        bs.balls_faced,
        bs.matches,
        bs.innings,
        bs.fours,
        bs.sixes,
        bs.dismissals,

        te.id AS tournament_id,
        te.name AS tournament_name,
        lower(te.dates) AS tournament_start,
        upper(te.dates) AS tournament_end,
        te.edition AS tournament_edition,
		te.season AS tournament_season

    FROM batting_stats bs
    JOIN tournament_editions te
        ON bs.tournament_edition_id = te.id
    WHERE bs.batter_id = #{playerId}
    """)
@Results({
    @Result(property = "runs", column = "runs"),
    @Result(property = "ballsFaced", column = "balls_faced"),
    @Result(property = "matches", column = "matches"),
    @Result(property = "innings", column = "innings"),
    @Result(property = "fours", column = "fours"),
    @Result(property = "sixes", column = "sixes"),
    @Result(property = "dismissals", column = "dismissals"),

    @Result(property = "tournament.id", column = "tournament_id"),
    @Result(property = "tournament.name", column = "tournament_name"),
    @Result(property = "tournament.start", column = "tournament_start"),
    @Result(property = "tournament.end", column = "tournament_end"),
    @Result(property = "tournament.edition", column = "tournament_edition"),
	@Result(property = "tournament.season", column = "tournament_season")
})
    List<BattingStatsSummary> getBattingStatsSummary(String playerId);

}
