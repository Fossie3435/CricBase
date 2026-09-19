import { useEffect, useState } from "react"
import { CombinedStats } from "../types/Statline"
import BattingStatsBlock from "./BattingStatsBlock";
import BowlingStatsBlock from "./BowlingStatsBlock";

interface PlayerStatsBlockProps {
	id: string;
}
function PlayerStatsBlock({ id }: PlayerStatsBlockProps ){
	const [stats, setStats] = useState<CombinedStats | null>(null);
	useEffect(() => {
		async function getPlayer() {
			const response = await fetch(
				`http://localhost:8080/stats/players/${id}`
			);
			const data = await response.json();
			console.log(data);
			setStats(data)
		}

		getPlayer()
	}, [id]);
	if(!stats) {
		return <p>Loading...</p>;
	}
	return (
		<div className="stats">
			<BattingStatsBlock battingStats={stats.battingStats} />
			<BowlingStatsBlock bowlingStats={stats.bowlingStats} />
		</div>
	)
}

export default PlayerStatsBlock;
