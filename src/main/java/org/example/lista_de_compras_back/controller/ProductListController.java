package org.example.lista_de_compras_back.controller;

import org.example.lista_de_compras_back.model.ProductLists;
import org.example.lista_de_compras_back.service.ProductListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
public class ProductListController {

    private final ProductListService productListService;

    public ProductListController(ProductListService productListService) {
        this.productListService = productListService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ProductLists>> getListsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(productListService.getListsByUserId(userId));
    }

    @PostMapping("/")
    public ResponseEntity<ProductLists> createList(@RequestBody ProductLists list) {
        return ResponseEntity.ok(productListService.createList(list));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteList(@PathVariable Long id) {
        productListService.deleteList(id);
        return ResponseEntity.ok().build();
    }
}

