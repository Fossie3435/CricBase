import { useEffect, useState } from "react"
import { Roster } from "../types/Roster";
import RosterList from "../components/RosterList";

function RosterSearchPage() {
	const [rosters, setRosters] = useState<Roster[] | null>(null);

    useEffect(() => {
        async function fetchRosters() {
            const response = await fetch(
                "http://localhost:8080/rosters/getall"
            );

            const data: Roster[] = await response.json();
            setRosters(data);
        }

        fetchRosters();
    }, []);
	if(rosters === null) {
		return <p>Loading...</p>
	}

	return (
		<div className="rosterList">
		{rosters.map(roster => (
			<RosterList roster={roster} />
		))}
		</div>
	)
}

export default RosterSearchPage
