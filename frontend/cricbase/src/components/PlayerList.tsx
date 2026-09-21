import type {Player} from '../types/Player'

interface PlayerListProps {
	player: Player;
	onClick: (player: Player) => void;
}

function PlayerList({player, onClick}: PlayerListProps) {
	return (
		<div>
			<button onClick={() => onClick(player)} >
				<h3>{player.nickname}</h3> 
				<h4><i>{player.name}</i></h4>
			</button>
		</div>
	)
}

export default PlayerList
