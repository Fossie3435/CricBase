import {useState} from 'react'
import PlayerList from './PlayerList'
import type { Player } from '../types/Player'

function PlayerSearch() {
	const [query, setQuery] = useState('')
	const [players, setPlayers] = useState<Player[]>([])

	async function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    	event.preventDefault()
		const response = await fetch(
			`http://localhost:8080/players/search?search=${encodeURIComponent(query)}`
		)
		const players: Player[] = await response.json()
		console.log(players)
		setPlayers(players)

	}


	return (
		<div>
			<form onSubmit={handleSubmit}>
				<input 
					type="text" 
					placeholder="search players..." 
					value={query} 
					onChange={(event) => setQuery(event.target.value)} 
				/>
				<input type="submit" value="Search" />
			</form>
        	{players.map(player => (
                <PlayerList
                    key={player.id}
                    player={player}
                />
            ))}
		</div>
	)
}

export default PlayerSearch
