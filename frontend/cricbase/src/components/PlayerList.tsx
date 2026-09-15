import type {Player} from '../types/Player'
import { useNavigate } from "react-router-dom";

interface PlayerListProps {
	player: Player
}

function PlayerList({player}: PlayerListProps) {
	const navigate = useNavigate();

	function handleClick() {
        navigate(`/players/${player.id}`);
    }

	return (
		<div>
			<button onClick={handleClick} >
				<h3>{player.nickname}</h3> 
				<h4><i>{player.name}</i></h4>
			</button>
		</div>
	)
}

export default PlayerList
