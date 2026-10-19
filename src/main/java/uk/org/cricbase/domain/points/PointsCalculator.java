package uk.org.cricbase.domain.points;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.org.cricbase.Models.Match;
import uk.org.cricbase.Models.ResultType;

public final class PointsCalculator {
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private PointsCalculator() {}

	public static Points calculatePoints(Match match, String criteriaJson) {
		return calculatePoints(match, getCriteria(criteriaJson));
	}
	
	private static Points calculatePoints(Match match, Map<ScoringEvent, PointCriteria> criteria) {
		int teamOnePoints = 0;
		int teamTwoPoints = 0;
		switch (match.getResultType()){
			case ResultType.RESULT:
				if(match.getWinner().getId() == match.getTeamOne().getId()) {
					teamOnePoints += criteria.get(ScoringEvent.MATCH_WIN).getValue();
					teamTwoPoints += criteria.get(ScoringEvent.MATCH_LOSS).getValue();
				}
				if(match.getWinner().getId() == match.getTeamTwo().getId()) {
					teamOnePoints += criteria.get(ScoringEvent.MATCH_LOSS).getValue();
					teamTwoPoints += criteria.get(ScoringEvent.MATCH_WIN).getValue();
				}
				break;
			case ResultType.TIE:
				teamOnePoints += criteria.get(ScoringEvent.MATCH_TIE).getValue();
				teamTwoPoints += criteria.get(ScoringEvent.MATCH_TIE).getValue();
				break;
			case ResultType.DRAW:
				teamOnePoints += criteria.get(ScoringEvent.MATCH_DRAW).getValue();
				teamTwoPoints += criteria.get(ScoringEvent.MATCH_DRAW).getValue();
				break;	
			case ResultType.NO_RESULT:
				teamOnePoints += criteria.get(ScoringEvent.MATCH_NO_RESULT).getValue();
				teamTwoPoints += criteria.get(ScoringEvent.MATCH_NO_RESULT).getValue();
				break;
			default:
				break;
			}

		return new Points(teamOnePoints, teamTwoPoints);
	}

	public static String getCriteriaJson(Map<ScoringEvent, PointCriteria> criteria) {
		try {
			return MAPPER.writeValueAsString(criteria);	
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Failed serialising point scoring criteria", e);
		}
	}

	public static String getBasicCriteriaJson(int win, int loss, int noResult) {
		return getCriteriaJson(getBasicCriteria(win, loss, noResult));
	}

	private static Map<ScoringEvent, PointCriteria> getCriteria(String criteriaJson) {
		try {
			return MAPPER.readValue(criteriaJson, new TypeReference<Map<ScoringEvent, PointCriteria>>() {});
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Failed serialising point scoring criteria", e);
		}
	}

	private static Map<ScoringEvent, PointCriteria> getBasicCriteria(int win, int loss, int noResult) {
		Map<ScoringEvent, PointCriteria> criteria = new HashMap<>();
		
		criteria.put(ScoringEvent.MATCH_WIN, new PointCriteria(win));
		criteria.put(ScoringEvent.MATCH_LOSS, new PointCriteria(loss));
		criteria.put(ScoringEvent.MATCH_NO_RESULT, new PointCriteria(noResult));
		criteria.put(ScoringEvent.MATCH_TIE, new PointCriteria(noResult));
		criteria.put(ScoringEvent.MATCH_DRAW, new PointCriteria(noResult));

		return criteria;
	}

}

