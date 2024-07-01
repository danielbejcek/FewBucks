import React from 'react';
import { Link } from 'react-router-dom';
import './NavigationBar.css';
import logo from './FewBucksLogo.png';
import login from './AccountImage_1.png';

const Navbar = () => {
    return (
        <nav className="navbar">
            <div className="navbar-brand">
                <img src={logo} alt='FewBucksLogo'/>
            </div>
            <ul className="navbar-links">
                <li>
                    <Link to="/">Home</Link>
                </li>
                <li>
                    <Link to="api/public/listProducts">Products</Link>
                </li>
                <li>
                    <Link to="/">About</Link>
                </li>
                <li>
                    <Link to="/">Contact us</Link>
                </li>
                {/* Add more links as needed */}
            </ul>
            <div className="navbar-login">
                <Link to="">
                    <button>
                        <img src={login} alt='AccountLogin'/>
                    </button>
                </Link>
            </div>
        </nav>
    );
};

export default Navbar;