import { useEffect, useState } from "react"
import { useParams } from "react-router-dom";
import { Roster } from "../types/Roster";
import PlayerSearch from "../components/PlayerSearch";
import { Player } from "../types/Player";
import PlayerList from "../components/PlayerList";

interface PlayerRosterRequest {
	player: string;
	start?: Date;
	end?: Date;
}

function RosterPage() {
	const [playersToAdd, setPlayersToAdd] = useState<Player[]>([]);
	const [roster, setRoster] = useState<Roster | null>(null);
	const { id } = useParams<{id: string}>();

	function handleAddingPlayer(player:Player) {
		setPlayersToAdd(prev => [...prev, player]);
	}
	function handleRemovingPlayer(player: Player) {
		setPlayersToAdd(prev =>
			prev.filter(p => p.id !== player.id)
		);
	}
	
	async function sendPlayers() {
		if(roster != null) {
			const request:PlayerRosterRequest[] = playersToAdd.map((player) => ({playerId: player.id }));

			await fetch(
				`http://localhost:8080/rosters/${roster.id}/players`, {
				method: "POST",
				headers: {
					"Content-Type": "application/json",
				},
				body: JSON.stringify(request)
			})
			setPlayersToAdd([]);
			getRoster();
		}
	}	
	async function getRoster() {
		const response = await fetch (
		`http://localhost:8080/rosters/${id}`
		);
		console.log(response);
		const data = await response.json();
		setRoster(data);
	}

	useEffect(() => {
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


			<h2>Players Being Added</h2>
			{playersToAdd.map((player) => (
				<PlayerList player={player} onClick={handleRemovingPlayer} />
			))}

			<button type="submit" onClick={sendPlayers}>Add players to roster</button>
			<hr/>
			<PlayerSearch handlePlayerClick={handleAddingPlayer} />
		
		</>
	)
}

export default RosterPage
