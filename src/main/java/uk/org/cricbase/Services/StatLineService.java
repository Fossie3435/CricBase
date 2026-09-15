package uk.org.cricbase.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import uk.org.cricbase.DTOs.BattingPerformanceSummary;
import uk.org.cricbase.DTOs.BattingStatsSummary;
import uk.org.cricbase.DTOs.BowlingPerformanceSummary;
import uk.org.cricbase.DTOs.BowlingStatsSummary;
import uk.org.cricbase.DTOs.CareerSummary;
import uk.org.cricbase.Mappers.BattingPerformanceMapper;
import uk.org.cricbase.Mappers.BowlingPerformanceMapper;
import uk.org.cricbase.Mappers.StatLineMapper;
import uk.org.cricbase.Models.BattingStatLine;
import uk.org.cricbase.Models.BowlingStatLine;
import uk.org.cricbase.Models.Player;

@Service
public class StatLineService {
	private StatLineMapper statLineMapper;
	private PlayerService playerService;
	private BattingPerformanceMapper battingPerformanceMapper;
	private BowlingPerformanceMapper bowlingPerformanceMapper;

	public StatLineService(StatLineMapper statLineMapper, PlayerService playerService,
			BattingPerformanceMapper battingPerformanceMapper, BowlingPerformanceMapper bowlingPerformanceMapper) {
		this.statLineMapper = statLineMapper;
		this.playerService = playerService;
		this.battingPerformanceMapper = battingPerformanceMapper;
		this.bowlingPerformanceMapper = bowlingPerformanceMapper;
	}

	public void calculateStatlines(long tournamentEditionId) {
		List<Player> players = playerService.getPlayersByTournamentEdition(tournamentEditionId);
		if(players.size() == 0) {
			return;
		}
		for(Player p : players) {
			this.calculateStatlinesForPlayer(tournamentEditionId, p.getId());
		}
	}

	public void calculateStatlinesForPlayer(long tournamentEditionId, String playerId) {
		int matches = this.statLineMapper.getMatchCount(tournamentEditionId, playerId);
		System.out.println("Player: " + playerId);
		if(matches == 0) {
			return;
		}
		List<BattingPerformanceSummary> battingPerformances = this.battingPerformanceMapper.getBattingPerformancesByPlayerIdAndTournamentEditionId(tournamentEditionId, playerId);
		if(battingPerformances.size() != 0) {
			BattingStatLine battingStatLine = new BattingStatLine(playerId, tournamentEditionId);
			for(BattingPerformanceSummary bp : battingPerformances) {
				battingStatLine.add(bp);
			}
			battingStatLine.setMatches(matches);
			this.statLineMapper.insertOrUpdateBattingStats(battingStatLine);
		}

		List<BowlingPerformanceSummary> bowlingPerformances = this.bowlingPerformanceMapper.getBowlingPerformancesByPlayerIdAndTournamentEditionId(tournamentEditionId, playerId);
		if(bowlingPerformances.size() != 0) {
			BowlingStatLine bowlingStatLine = new BowlingStatLine(playerId, tournamentEditionId);
			for(BowlingPerformanceSummary bp : bowlingPerformances) {
				bowlingStatLine.add(bp);
			}
			bowlingStatLine.setMatches(matches);
			this.statLineMapper.insertOrUpdateBowlingStats(bowlingStatLine);	
		}
	}

    public CareerSummary getCareerStats(String playerId) {
		List<BattingStatsSummary> battingStats = this.statLineMapper.getBattingStatsSummary(playerId);
		List<BowlingStatsSummary> bowlingStats = this.statLineMapper.getBowlingStatsSummary(playerId);
		return new CareerSummary(bowlingStats, battingStats);
    }
}
