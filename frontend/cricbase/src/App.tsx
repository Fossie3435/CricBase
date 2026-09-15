import NavBar from './components/NavBar'
import PlayerSearchPage from './components/PlayerSearchPage' 
import PlayerPage from './pages/PlayerPage'
import HomePage from './pages/HomePage'
import { BrowserRouter,  Routes, Route } from 'react-router-dom'
import RosterSearchPage from './pages/RosterSearchPage'
import RosterPage from './pages/RosterPage'

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
            </Routes>
		</BrowserRouter>
		</>
	)
}

export default App
