import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useLocation } from 'react-router-dom';

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
            <ul>
                {products.map(product => (
                    <li key={product.id}>
                        <p>Name: {product.itemName}</p>
                        <p>Price: ${product.itemPrice}</p>
                        <p>Amount: {product.itemAmount}</p>
                        <p>In Stock: {product.itemInStock ? 'Yes' : 'No'}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ProductList;