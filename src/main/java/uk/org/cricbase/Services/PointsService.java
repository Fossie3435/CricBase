package uk.org.cricbase.Services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import uk.org.cricbase.DTOs.RosterPointsLeaderboardEntry;
import uk.org.cricbase.DTOs.RosterSummaryBasic;
import uk.org.cricbase.Mappers.MatchMapper;
import uk.org.cricbase.Mappers.RosterMapper;
import uk.org.cricbase.Mappers.TeamMapper;
import uk.org.cricbase.Models.Match;
import uk.org.cricbase.domain.points.Points;
import uk.org.cricbase.domain.points.PointsCalculator;

@Service
public class PointsService {
    private final MatchMapper matchMapper;
	private final TeamMapper teamMapper;
	private final RosterMapper rosterMapper;
	
	public PointsService(MatchMapper matchMapper, TeamMapper teamMapper, RosterMapper rosterMapper) {
		this.matchMapper = matchMapper;
		this.teamMapper = teamMapper;
		this.rosterMapper = rosterMapper;
	}

	public void calculatePointsForTournamentEdition(long tournamentEditionId) {
		String criteriaJson = PointsCalculator.getBasicCriteriaJson(2, 0, 1);
		List<Long> matchIds = matchMapper.findGroupStageMatchIdsByTournamentEditionId(tournamentEditionId);
		System.out.println(matchIds.size());
		for(long matchId:matchIds) {
			this.calculatePointsForMatch(matchId, criteriaJson);
		}
	}

	public void calculatePointsForMatch(long matchId, String criteriaJson) {
		Match match = matchMapper.findMatchById(matchId);

		if(match.getTeamTwo() == null || match.getTeamOne() == null) {
			throw new IllegalArgumentException();
		}
		Points points = PointsCalculator.calculatePoints(match, criteriaJson);
		System.out.println(points.teamOnePoints());
		System.out.println(points.teamTwoPoints());

		teamMapper.updateTeamPoints(match.getTeamOne().getId(), points.teamOnePoints());
		teamMapper.updateTeamPoints(match.getTeamTwo().getId(), points.teamTwoPoints());
	}

	public List<RosterPointsLeaderboardEntry> retrievePointsForTournamentEdition(long tournamentEditionId) {
		return rosterMapper.findPointsTableByTournamentEditionId(tournamentEditionId);	
	}
}
