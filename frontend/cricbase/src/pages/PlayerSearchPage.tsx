import { useNavigate } from "react-router-dom";
import PlayerSearch from "../components/PlayerSearch";
import { Player } from "../types/Player";
import PlayersMissingNicknames from "../components/PlayersMissingNicknames";

function PlayerSearchPage() {
	const navigate = useNavigate();
	function handleSearch(player: Player) {
		navigate(`/players/${player.id}`);
	}

	return (
		<>
		<PlayerSearch handlePlayerClick={handleSearch} />

		<PlayersMissingNicknames />
		</>
	)
}

export default PlayerSearchPage
