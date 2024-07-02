import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '@fortawesome/fontawesome-free/css/all.min.css';
import './NavigationBar.css';
import logo from './FewBucksLogo.png';
import login from './AccountImage_1.png';

const Navbar = () => {
    const [search, setSearch] = useState('');
    const navigate = useNavigate();

    const handleSearchSubmit = (event) => {
        event.preventDefault();
        navigate(`api/public/listProducts?search=${search}`);
    };

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
                    <Link to="/api/public/listProducts">Products</Link>
                </li>
                <li>
                    <Link to="/">About</Link>
                </li>
                <li>
                    <Link to="/">Contact us</Link>
                </li>

            </ul>
            <div className="navbar-search">
                <div className="box">
                    <form name="search" onSubmit={handleSearchSubmit}>
                        <input
                            type="text"
                            className="input"
                            name="search"
                            onChange={(e) => setSearch(e.target.value)}
                        />
                        <i className="fas fa-search search-icon"></i>
                    </form>

                </div>
            </div>

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