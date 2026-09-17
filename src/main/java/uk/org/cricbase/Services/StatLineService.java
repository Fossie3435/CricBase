package uk.org.cricbase.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import uk.org.cricbase.DTOs.BattingPerformanceSummary;
import uk.org.cricbase.DTOs.BattingStatsSummary;
import uk.org.cricbase.DTOs.BowlingPerformanceSummary;
import uk.org.cricbase.DTOs.BowlingStatsSummary;
import uk.org.cricbase.DTOs.CareerSummary;
import uk.org.cricbase.DTOs.StatLeaderboard;
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

    public List<StatLeaderboard> getStatLeadersForTournamentEdition(long editionId) {
   		ArrayList<StatLeaderboard> leaderboards = new ArrayList<>();
		leaderboards.add(this.getHighestRunScorersForTournamentEdition(editionId, 5));
		leaderboards.add(this.getHighestWicketTakersForTournamentEdition(editionId, 5));
		leaderboards.add(this.getHighestStrikeRatesForTournamentEdition(editionId, 5));
		leaderboards.add(this.getLowestEconomyRatesForTournamentEdition(editionId, 5));

		return leaderboards;
	}

	public StatLeaderboard getHighestRunScorersForTournamentEdition(long editionId, int entries) {
		StatLeaderboard sb = new StatLeaderboard("Highest Run Scorers", "runs");
		sb.setEntries(this.statLineMapper.getHighestRunScorersByTournamentEditionId(editionId, entries));
		sb.setDecimalPlaces(0);
		sb.generateEntryNumbers();
		return sb;
	}	
	public StatLeaderboard getHighestWicketTakersForTournamentEdition(long editionId, int entries) {
		StatLeaderboard sb = new StatLeaderboard("Highest Wicket Takers", "wickets");
		sb.setEntries(this.statLineMapper.getHighestWicketTakersByTournamentEditionId(editionId, entries));
		sb.setDecimalPlaces(0);
		sb.generateEntryNumbers();
		return sb;
	}
	public StatLeaderboard getHighestStrikeRatesForTournamentEdition(long editionId, int entries) {
		StatLeaderboard sb = new StatLeaderboard("Highest Strike Rates", "SR");
		sb.setEntries(this.statLineMapper.getHighestStrikeRatesByTournamentEditionId(editionId, entries));
		sb.setDecimalPlaces(0);
		sb.generateEntryNumbers();
		return sb;
	}	
	public StatLeaderboard getLowestEconomyRatesForTournamentEdition(long editionId, int entries) {
		StatLeaderboard sb = new StatLeaderboard("Lowest Economy Rates", "ER");
		sb.setEntries(this.statLineMapper.getLowestEconomyRatesByTournamentEditionId(editionId, entries));
		sb.setDecimalPlaces(2);
		sb.generateEntryNumbers();
		return sb;
	}
}
