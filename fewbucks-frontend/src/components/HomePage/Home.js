import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Home.css';
import Navbar from './NavigationBar';

const Home = () => {
    const navigate = useNavigate();

    const navigateToListProducts = () => {
        navigate('api/public/listProducts');
    };

    return (
        <div className="home-container">
            <Navbar />
            <div className="home-content">

            </div>
        </div>
    );
};

export default Home;