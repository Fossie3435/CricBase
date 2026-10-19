import { RosterPointsLeaderboardEntry } from "../types/Tournament";

interface PointsTableProps {
	pointsTable: RosterPointsLeaderboardEntry[];
}

function PointsTable({pointsTable}: PointsTableProps) {
	
	return (<table className="pointsTable">
		<tr>
			<th>Name</th>
			<th>Points</th>
		</tr>

		{pointsTable.map((entry) => (
		<tr>	
			<td>{entry.name}</td>
			<td>{entry.points}</td>
		</tr>
		))}
	</table>)
}

export default PointsTable;
