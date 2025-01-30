package org.example.lista_de_compras_back.service;

import org.example.lista_de_compras_back.model.Products;
import org.example.lista_de_compras_back.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Products> getProductsByListId(Long listId) {
        return productRepository.findByListId(listId);
    }

    public Products addProduct(Products products) {
        return productRepository.save(products);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Products> updateProduct(Long id, Products updatedProducts) {
        return productRepository.findById(id).map(products -> {
            products.setName(updatedProducts.getName());
            products.setQuantity(updatedProducts.getQuantity());
            products.setUnit(updatedProducts.getUnit());
            products.setComprado(updatedProducts.getComprado());
            return productRepository.save(products);
        });
    }
}
