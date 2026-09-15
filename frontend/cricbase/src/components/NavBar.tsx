import { Link } from "react-router-dom";
import "./NavBar.css";
import logo from "../assets/logo_long.svg";

function Navbar() {
    return (
        <nav className="navbar">
            <Link to="/" className="navbar-brand">
                <img src={logo} alt="CricBase Logo"/>
            </Link>

            <div className="navbar-links">
                <Link to="/players"><h1>Players</h1></Link>
				<Link to="/tournaments"><h1>Tournaments</h1></Link>
				<Link to="/records"><h1>Stat Leaders</h1></Link>
				<Link to="/rosters"><h1>Rosters</h1></Link>
            </div>
        </nav>
    );
}

export default Navbar;
