package uk.org.cricbase.Services;

import java.util.List;

import javax.management.AttributeNotFoundException;

import org.springframework.stereotype.Service;

import uk.org.cricbase.DTOs.RosterPointsLeaderboardEntry;
import uk.org.cricbase.Mappers.MatchMapper;
import uk.org.cricbase.Mappers.PointsMapper;
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
	private final PointsMapper pointsMapper;
	
	public PointsService(MatchMapper matchMapper, TeamMapper teamMapper, RosterMapper rosterMapper, PointsMapper pointsMapper) {
		this.matchMapper = matchMapper;
		this.teamMapper = teamMapper;
		this.rosterMapper = rosterMapper;
		this.pointsMapper = pointsMapper;
	}

	public void calculatePointsForTournamentEdition(long tournamentEditionId) throws AttributeNotFoundException {
		String criteriaJson = this.pointsMapper.findPointsCriteriaByTournamentEditionId(tournamentEditionId);
		if (criteriaJson == null) {
			throw new AttributeNotFoundException("missing points criteriaJson")
		}
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
