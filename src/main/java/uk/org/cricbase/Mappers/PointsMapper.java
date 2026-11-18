package uk.org.cricbase.Mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PointsMapper {
	@Update("""
		UPDATE tournament_editions
		SET points_criteria = CAST(#{criteriaJson} AS jsonb)
		WHERE id = #{teId}
	""")
    void updatePointCriteria(@Param("teId") long tournamentEditionId, @Param("criteriaJson") String criteriaJson);

	@Select("""
		SELECT points_criteria::text
		FROM tournament_editions
		WHERE id = #{teId}
	""")
	String findPointsCriteriaByTournamentEditionId(@Param("teId") long tournamentEditionId);
}
