import { RosterPointsLeaderboardEntry } from "../types/Tournament";

interface PointsTableProps {
	pointsTable: RosterPointsLeaderboardEntry[];
}

function PointsTable({pointsTable}: PointsTableProps) {
	
	return (<table className="pointsTable">
		<tr>
			<th>Name</th>
			<th>W</th>
			<th>L</th>
			<th>NR</th>
			<th>Points</th>
		</tr>

		{pointsTable.map((entry) => (
		<tr>	
			<td>{entry.name}</td>
			<td>{entry.matches}</td>
			<td>{entry.wins}</td>
			<td>{entry.losses}</td>
			<td>{entry.noResults}</td>
			<td>{entry.points}</td>
		</tr>
		))}
	</table>)
}

export default PointsTable;
