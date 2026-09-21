import { useNavigate } from "react-router-dom";
import PlayerSearch from "../components/PlayerSearch";
import { Player } from "../types/Player";

function PlayerSearchPage() {
	const navigate = useNavigate();
	function handleSearch(player: Player) {
		navigate(`/players/${player.id}`);
	}

	return (
		<PlayerSearch handlePlayerClick={handleSearch} />
	)
}

export default PlayerSearchPage
