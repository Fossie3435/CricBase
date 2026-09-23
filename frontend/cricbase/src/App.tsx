import NavBar from './components/NavBar'
import PlayerPage from './pages/PlayerPage'
import PlayerSearchPage from './pages/PlayerSearchPage'
import HomePage from './pages/HomePage'
import { BrowserRouter,  Routes, Route } from 'react-router-dom'
import RosterSearchPage from './pages/RosterSearchPage'
import RosterPage from './pages/RosterPage'
import TournamentSearchPage from './pages/TournamentSearchPage'
import TournamentEditionPage from './pages/TournamentEditionPage'
import MatchPage from './pages/MatchPage'
import BattingLeaderboard from './pages/BattingLeaderboard'
import BowlingLeaderboard from './pages/BowlingLeaderboard'
import TournamentPage from './pages/TournamentPage'

function App() {
  	return (
		<>
		<BrowserRouter>
		<NavBar />
            <Routes>
				<Route path="/" element={<HomePage />} />
                <Route path="/players" element={<PlayerSearchPage />} />
                <Route path="/players/:id" element={<PlayerPage />} />
				<Route path="/rosters" element={<RosterSearchPage />} />
				<Route path="/rosters/:id" element={<RosterPage />} />
				<Route path="/tournaments" element={<TournamentSearchPage />} />
				<Route path="/tournaments/editions/:id" element={<TournamentEditionPage />} />
				<Route path="/tournaments/:id" element={<TournamentPage />} />
				<Route path="/matches/:id" element={<MatchPage/>} />
				<Route path="/tournaments/editions/:id/leaderboards/batting" element={<BattingLeaderboard type="editions" />} />
				<Route path="/tournaments/:id/leaderboards/batting" element={<BattingLeaderboard type="tournaments" />} />
				<Route path="/tournaments/editions/:id/leaderboards/bowling" element={<BowlingLeaderboard type="editions"/>} />
				<Route path="/tournaments/:id/leaderboards/bowling" element={<BowlingLeaderboard type="tournaments" />} />
            </Routes>
		</BrowserRouter>
		</>
	)
}

export default App
