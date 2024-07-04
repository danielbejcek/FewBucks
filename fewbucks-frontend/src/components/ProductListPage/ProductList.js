import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useLocation } from 'react-router-dom';
import './ProductList.css';


const useQuery = () => {
    return new URLSearchParams(useLocation().search);
};

const ProductList = () => {
    const [products, setProducts] = useState([]);
    const query = useQuery();
    const searchQuery = query.get('search') || '';

    useEffect(() => {
        const fetchProducts = () => {
            let url = '/api/public/listProducts';
            if (searchQuery) {
                url += `?search=${searchQuery}`;
            }

            axios.get(url)
                .then(response => {
                    setProducts(response.data);
                })
                .catch(error => {
                    console.error('There was an error fetching the products.', error);
                });
        };

        fetchProducts();
    }, [searchQuery]);

    return (
        <div>
            <h1>Product List</h1>
            <div className="product-list">
                {products.map(product => (
                    <div className="product-card" key={product.id}>
                        <div className="product-header">
                            <h2>{product.itemName}</h2>
                            <div className="product-details">
                                <div className="product-detail">
                                    <span className="product-key">Price:</span>
                                    <span className="product-value">${product.itemPrice}</span>
                                </div>
                                <div className="product-detail">
                                    <span className="product-key">Amount:</span>
                                    <span className="product-value">{product.itemAmount}</span>
                                </div>
                                <div className="product-detail">
                                    <span className="product-key">In Stock:</span>
                                    <span className="product-value">{product.itemInStock ? 'Yes' : 'No'}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ProductList;