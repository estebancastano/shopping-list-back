package org.example.lista_de_compras_back.controller;

import org.example.lista_de_compras_back.model.Products;
import org.example.lista_de_compras_back.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{listId}")
    public ResponseEntity<List<Products>> getProductsByListId(@PathVariable Long listId) {
        return ResponseEntity.ok(productService.getProductsByListId(listId));
    }

    @PostMapping("/")
    public ResponseEntity<Products> addProduct(@RequestBody Products products) {
        return ResponseEntity.ok(productService.addProduct(products));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable Long id, @RequestBody Products updatedProducts) {
        Optional<Products> product = productService.updateProduct(id, updatedProducts);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

