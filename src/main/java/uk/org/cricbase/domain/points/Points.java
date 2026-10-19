package uk.org.cricbase.domain.points;

public record Points(
	int teamOnePoints,
	int teamTwoPoints
) {
	public int getTotalPoints() {
		return teamOnePoints() + teamTwoPoints();
	}
}
