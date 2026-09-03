/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Mappers;


import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import uk.org.cricbase.DTOs.DetailedInningSummary;
import uk.org.cricbase.DTOs.DetailedMatchSummary;
import uk.org.cricbase.DTOs.InningSummary;
import uk.org.cricbase.DTOs.MatchSummary;
import uk.org.cricbase.DTOs.TeamSummary;
import uk.org.cricbase.Models.BattingPerformance;
import uk.org.cricbase.Models.BowlingPerformance;
import uk.org.cricbase.Models.Delivery;
import uk.org.cricbase.Models.FallOfWicket;
import uk.org.cricbase.Models.Inning;
import uk.org.cricbase.Models.Match;
import uk.org.cricbase.Models.Over;
import uk.org.cricbase.Models.Player;
import uk.org.cricbase.Models.Team;
import uk.org.cricbase.Models.Wicket;


/**
 *
 * @author Benjamin
 */
@Mapper
public interface MatchMapper {
    @Results(id = "matchResult", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "ballsPerOver", column = "balls_per_over"),
        @Result(property = "tournament", column = "tournament"),
        @Result(property = "season", column = "season"),
        @Result(property = "matchType", column = "type"),
        @Result(property = "matchFormat", column = "match_format"),
        @Result(property = "gender", column = "gender"),
        @Result(property = "matchNumber", column = "match_number"),
        @Result(property = "overs", column = "overs"),
        @Result(property = "teamType", column = "team_type")
    })
    @Select("""
            SELECT 
            id,
            balls_per_over,
            gender,
            match_number,
            match_format,
            overs,
            season,
            team_type,
            tournament,
            type
            FROM matches
            WHERE season = #{season} AND match_number = #{matchNumber} AND gender = #{tournament}
            """)
    Match findMatchByInfo(String tournament, String season, int matchNumber);
    
    @Results(id = "matchSummaryResult", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "tournament", column = "tournament"),
        @Result(property = "season", column = "season"),
        @Result(property = "matchType", column = "match_type"),
        @Result(property = "gender", column = "gender"),
        @Result(property = "matchNumber", column = "match_number"),
        @Result(property = "overs", column = "overs"),
        @Result(property = "ground.id", column = "ground_id"),
        @Result(property = "ground.name", column = "ground_name"),
        @Result(property = "ground.city", column = "ground_city"),
        @Result(property = "innings", column = "id", many=@Many(select = "uk.org.cricbase.Mappers.MatchMapper.findInningSummariesByMatchId"))
    })
    @Select("""
            SELECT
                m.id,
                m.tournament,
                m.season,
                m.match_type,
                m.gender,
                m.match_number,
                m.overs,
                ground_id,
                g.name AS ground_name,
                g.city AS ground_city
            FROM matches m JOIN grounds g ON m.ground_id = g.id
            WHERE m.id = #{id}
            """)
    MatchSummary findMatchSummaryById(long id);
    
    @Results(id = "inningSummaryResult", value = {
        @Result(property = "teamName", column = "name"),
        @Result(property = "runs", column = "runs"),
        @Result(property = "wickets", column = "wickets_taken")
    })
    @Select("""
            SELECT
                teams.name,
                innings.total_runs,
                innings.wickets_taken
            FROM innings
            JOIN teams ON innings.batting_team_id = teams.id
            WHERE innings.match_id = #{matchId}
            """)
    List<InningSummary> findInningSummariesByMatchId(long matchId);
    
    @Results(id = "detailedMatchSummary", value = {
        @Result(property = "matchSummary", column = "id", one=@One(select = "uk.org.cricbase.Mappers.MatchMapper.findMatchSummaryById")),
        @Result(property = "innings", column = "id", many=@Many(select = "uk.org.cricbase.Mappers.MatchMapper.findDetailedInningSummaryByMatchId")),
        @Result(property = "teams", column = "id", many=@Many(select = "uk.org.cricbase.Mappers.MatchMapper.findTeamSummariesByMatchId"))
    })
    @Select("""
            SELECT id
            FROM matches
            WHERE id = #{id}
            """)
    DetailedMatchSummary findDetailedMatchSummaryById(long id);
    
    @Results(id = "detailedInningSummary", value = {
        @Result(property = "id", column = "id"),
        @Result(property = "total", column = "total_runs"),
        @Result(property = "wickets", column = "wickets_taken"),
        @Result(property = "runs", column = "runs"),
        @Result(property = "byes", column = "byes"),
        @Result(property = "legbyes", column = "leg_byes"),
        @Result(property = "wides", column = "wides"),
        @Result(property = "noballs", column = "no_balls"),
        @Result(property = "penaltyRuns", column = "penaltyRuns"),
        @Result(property = "battingScorecard", column = "id", many=@Many(select = "uk.org.cricbase.Mappers.BattingPerformanceMapper.findBattingPerformancesByInningId")),
        @Result(property = "bowlingScorecard", column = "id", many=@Many(select = "uk.org.cricbase.Mappers.BowlingPerformanceMapper.findBowlingPerformancesByInningId")),
        @Result(property = "fallOfWickets", column = "id", many=@Many(select = "uk.org.cricbase.Mappers.FallOfWicketMapper.findFallOfWicketSummariesByInningId"))
    })
    @Select("""
            SELECT
            id,
            total_runs,
            runs,
            wickets_taken,
            byes,
            leg_byes,
            wides,
            no_balls,
            penalty_runs
            FROM innings
            WHERE match_id = #{matchId};
            """)
    DetailedInningSummary findDetailedInningSummaryByMatchId(long matchId);
    
    @Results(id = "teamSummary", value = {
        @Result(property = "name", column = "name"),
        @Result(property = "players", column = "id", many=@Many(select = "uk.org.cricbase.Mappers.PlayerMapper.findPlayersByTeamId"))
    })
    @Select("""
            SELECT 
            name,
            id
            FROM teams
            WHERE match_id = #{matchId}
            """)
    List<TeamSummary> findTeamSummariesByMatchId(long matchId);
    
    @Insert("""
            INSERT INTO matches
            (balls_per_over, gender, match_number, match_type, overs, season, team_type, tournament) 
            VALUES
            (#{ballsPerOver}, #{gender}, #{matchNumber}, #{matchFormat}, #{overs}, #{season}, #{teamType}, #{tournament})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertMatch(Match match);

    @Update("""
    UPDATE matches
    SET
        balls_per_over = #{ballsPerOver},
        gender = #{gender},
        match_number = #{matchNumber},
        match_format = #{matchFormat},
        overs = #{overs},
        season = #{season},
        team_type = #{teamType},
        tournament = #{tournament}
    WHERE id = #{id}
    """)
    void update(Match match);
    
    @Update("""
        UPDATE matches
        SET
            ground_id = #{ground.id}
        WHERE id = #{id}
        """)
    void updateGround(Match match);
    
    @Update("""
        UPDATE matches
        SET
            player_of_the_match_id = #{playerOfTheMatch.id}
        WHERE id = #{id}
            """)
    void updatePlayerOfTheMatch(Match match);

    @Delete("DELETE FROM users WHERE id=#{id}")
    void delete(int id);
    
    @Insert("""
            INSERT INTO Innings
            (match_id, byes, leg_byes, no_balls, penalty_runs, runs, wickets_taken, wides, batting_team_id, bowling_team_id)
            VALUES
            (#{match.id}, #{byes}, #{legbyes}, #{noballs}, #{penaltyRuns}, #{runs}, #{wicketsTaken}, #{wides}, #{battingTeam.id}, #{bowlingTeam.id})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertInning(Inning inning);
    
    @Insert("""
            INSERT INTO batting_performances
            (balls_faced, batting_position, fours, runs, sixes, inning_id, batter_id)
            VALUES
            (#{ballsFaced}, #{battingPosition}, #{fours},#{runs} , #{sixes}, #{inning.id}, #{batter.id})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertBattingPerformance(BattingPerformance battingPerformance);
    
    @Insert("""
            INSERT INTO bowling_performances
            (balls_bowled, bowling_position, byes, dots, fours_conceded, leg_byes, maidens, no_balls, runs_conceded, sixes_conceded, wicket_count, wides, inning_id, bowler_id)
            VALUES
            (#{ballsBowled}, #{bowlingPosition}, #{byes}, #{dots}, #{foursConceded}, #{legbyes}, #{maidens}, #{noballs}, #{runsConceded}, #{sixesConceded}, #{wicketCount}, #{wides}, #{inning.id}, #{bowler.id})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertBowlingPerformance(BowlingPerformance bowlingPerformance);
    
    @Insert("""
            INSERT INTO fall_of_wickets
            (total, delivery, wicket, inning_id, batter_id)
            VALUES
            (#{currentTotal},#{delivery}, #{wicket}, #{inning.id}, #{batterOut.id}) 
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertFallOfWicket(FallOfWicket fallOfWicket);
    
    
    @Insert("""
            INSERT INTO overs
            (byes, leg_byes, no_balls, over, penalty_runs, runs, wides, inning_id)
            VALUES
            (#{byes}, #{legbyes}, #{noballs}, #{over}, #{penaltyRuns}, #{runs}, #{wides}, #{inning.id})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertOver(Over over);
    
    @Insert("""
            INSERT INTO wickets
            (dismissal_type, batting_performance_id, bowling_performance_id, bowler_id, batter_id)
            VALUES
            (#{dismissalType}, #{battingPerformance.id}, #{bowlingPerformance.id}, #{bowler.id}, #{batter.id})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertWicket(Wicket wicket); 
    
    @Insert("""
            INSERT INTO deliveries
            (byes, leg_byes, no_balls, penalty_runs, runs, total_delivery_count, wides, batting_performance_id, bowling_performance_id, over_id, wicket_id, bowler_id, batter_id, non_striker_id, delivery_count)
            VALUES
            (#{byes}, #{legbyes}, #{noballs}, #{penaltyRuns}, #{runs}, #{totalDeliveryCount}, #{wides}, #{battingPerformance.id}, #{bowlingPerformance.id}, #{over.id}, #{wicket.id}, #{bowler.id}, #{batter.id}, #{nonStriker.id}, #{deliveryCount} )
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertDelivery(Delivery delivery);
    
    @Insert("""
            INSERT INTO teams
            (name, match_id)
            VALUES
            (#{name}, #{match.id})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertTeam(Team team);
    
    @Select("""
            SELECT 
            id
            FROM teams
            WHERE match_id = #{matchId} AND name = #{name}
            """)
    long findTeamIdByInfo(long matchId, String name);
    
    @Insert("""
            INSERT INTO players_teams
            (player_id, team_id)
            VALUES
            (#{player.id}, #{team.id})
            """)
    void insertPlayerTeam(Player player, Team team);
    
    @Update("""
            UPDATE matches
                SET 
                    toss_decision = #{tossDecision}::toss_decision,
                    toss_winner = #{tossWinner.id}
                WHERE id = #{id}
            """)
    void updateToss(Match match);
    
    @Update("""
            UPDATE matches
                SET 
                    winner = #{winner.id},
                    runs_wm = #{runsMargin},
                    innings_wm = #{inningsMargin},
                    wickets_wm = #{wicketsMargin}
                WHERE id = #{id}
            """)
    void updateWin(Match match);
    
    @Update("""
            UPDATE matches
                SET
                    result_type = #{resultType}::result_type
                WHERE id = #{id}
            """)
    void updateResultType(Match match);
    
    @Update("""
            UPDATE matches
                SET
                    type = #{matchType}::match_type
            WHERE id = #{id}
            """)
    void updateMatchType(Match match);
    
    @Update("""
            UPDATE matches
                SET
                    date = #{date}
                WHERE id = #{id}
            """)
    void updateDate(Match match);
}
