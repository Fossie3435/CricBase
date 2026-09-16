import { useParams } from "react-router-dom"
import { Player } from "../types/Player";
import  PlayerStatsBlock  from "../components/PlayerStatsBlock";
import { useState, useEffect } from "react";

function PlayerPage() {
	const { id } = useParams<{id: string}>();

	const [player, setPlayer] = useState<Player | null>(null);
	
	useEffect(() => {
		async function getPlayer() {
			const response = await fetch(
				`http://localhost:8080/players/${id}`
			);
			const data = await response.json();

			setPlayer(data)
		}

		getPlayer()
	}, [id]);
	if(!id) {
		return <p>Invalid Player ID </p>;
	}
	if(!player) {
		return <p>Loading...</p>;
	}
	return ( 
		<div className="playerPage">
			<h1>{player.nickname}</h1>
			<h3><i>{player.name}</i></h3>
			<PlayerStatsBlock id={id}/>
		</div>
	)
}

export default PlayerPage
