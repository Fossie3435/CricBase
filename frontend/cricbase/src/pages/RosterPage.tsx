import { useEffect, useState } from "react"
import { useParams } from "react-router-dom";
import { Roster } from "../types/Roster";
import PlayerSearchPage from "../components/PlayerSearchPage";

function RosterPage() {
	const [playersAdded, setPlayersAdded] = useState(null);
	const [roster, setRoster] = useState<Roster | null>(null);
	const { id } = useParams<{id: string}>();

	useEffect(() => {
		async function getRoster() {
			const response = await fetch (
			`http://localhost:8080/rosters/${id}`
			);
			console.log(response);
			const data = await response.json();
			setRoster(data);
		}
		getRoster();
	}, [id])
	if(id === undefined) {
		return <p>Invalid roster id</p>
	}
	if(roster === null) {
		return <p>Loading...</p>
	}
	return (
		<>
			<h1>{roster.name}</h1>
			{roster.players.map((player) => (
				<h3>{player.nickname}</h3>
			))}
		</>
	)
}

export default RosterPage
