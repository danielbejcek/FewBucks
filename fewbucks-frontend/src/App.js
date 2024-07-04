import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import Home from './components/HomePage/Home';
import ProductList from './components/ProductListPage/ProductList';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route path="api/public/listProducts" element={<ProductList />} />
        </Routes>
      </div>
    </Router>
  );
}
export default App;