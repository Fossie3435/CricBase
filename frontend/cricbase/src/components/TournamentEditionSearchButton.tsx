import { useNavigate } from "react-router-dom";
import { TournamentEdition } from "../types/Tournament"

interface TournamentEditionSearchButtonProps {
	tournamentEdition: TournamentEdition;
}

function TournamentEditionSearchButton( {tournamentEdition}: TournamentEditionSearchButtonProps) {
	const navigate = useNavigate();

	function handleClick() {
		navigate(`editions/${tournamentEdition.id}`)
	}

	return (
		<button onClick={handleClick}>
			<h4>{tournamentEdition.name} {tournamentEdition.season}</h4>
			<h5>{tournamentEdition.start}-{tournamentEdition.end}</h5>
			<h5>Edition {tournamentEdition.edition}</h5>
		</button>
	)
}

export default TournamentEditionSearchButton
