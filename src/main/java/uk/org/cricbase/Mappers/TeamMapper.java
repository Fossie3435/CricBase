package uk.org.cricbase.Mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TeamMapper {

	@Update("""
		UPDATE teams
		SET points = #{points}
		WHERE id = #{tId}
	""")
	void updateTeamPoints(@Param("tId") long teamId, @Param("points") Integer points);
}
