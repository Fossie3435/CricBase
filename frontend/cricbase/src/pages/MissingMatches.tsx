import { useEffect, useState } from "react" 
import { TournamentEdition } from "../types/Tournament";

interface TournamentEntry {
	tournamentEditionSummary: TournamentEdition;
	missingMatches: number[];
}

function MissingMatches() {
	const [missingMatches, setMissingMatches] = useState<TournamentEntry[]>([])
	
	useEffect(() => {
		async function getMissingMatches() {
			const response = await fetch(
			`http://localhost:8080/matches/missing`);
			const data = await response.json();
			console.log(data);
			setMissingMatches(data);
		}
		getMissingMatches()
	});


	return <>
		<h1>Missing Matches</h1>
		<div>
			{missingMatches.map((missingMatch) => (
				<div>
				<h2>{missingMatch.tournamentEditionSummary.name} {missingMatch.tournamentEditionSummary.season}</h2>
				<ul>
                    {missingMatch.missingMatches.map((matchNumber) => (
                        <li key={matchNumber}>
                            Match {matchNumber}
                        </li>
                    ))}
                </ul>
				</div>
			))}	
		</div>
	</>
}

export default MissingMatches;
