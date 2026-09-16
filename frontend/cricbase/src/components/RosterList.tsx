import { Link, useNavigate } from "react-router-dom";
import { Roster } from "../types/Roster";

interface RosterListProps {
	roster: Roster;
}

function RosterList( { roster }: RosterListProps) {
	const navigate = useNavigate();
	return (
		<>
			<button onClick={() => navigate(`/rosters/${roster.id}`)}>
			<h3>{roster.name}</h3>
			{roster.players.map(player => (
				<h4 key={player.id}>{player.nickname}</h4>	
			))}
			</button>
		</>
	)
}

export default RosterList
