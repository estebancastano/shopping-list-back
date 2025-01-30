package org.example.lista_de_compras_back.service;

import org.example.lista_de_compras_back.model.ProductLists;
import org.example.lista_de_compras_back.repository.ProductListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductListService {

    @Autowired
    private ProductListRepository productListRepository;

    public List<ProductLists> getListsByUserId(Long userId) {
        return productListRepository.findByUserId(userId);
    }

    public ProductLists createList(ProductLists list) {
        return productListRepository.save(list);
    }

    public void deleteList(Long id) {
        productListRepository.deleteById(id);
    }
}

