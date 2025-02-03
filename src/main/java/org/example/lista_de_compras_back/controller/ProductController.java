package org.example.lista_de_compras_back.controller;

import org.example.lista_de_compras_back.DTO.ProductRequest;
import org.example.lista_de_compras_back.model.ProductLists;
import org.example.lista_de_compras_back.model.Products;
import org.example.lista_de_compras_back.repository.ProductListRepository;
import org.example.lista_de_compras_back.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductListRepository productListRepository;
    @Autowired
    public ProductController(ProductService productService, ProductListRepository productListRepository) {
        this.productService = productService;
        this.productListRepository = productListRepository;
    }

    @GetMapping("/user-products")
    public ResponseEntity<List<Products>> getProductsForCurrentUser() {
        String username = getAuthenticatedUser(); // Obtener el usuario autenticado
        List<Products> userProducts = productService.getProductsByUser(username);
        return ResponseEntity.ok(userProducts);
    }

    public String getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            System.out.println("Usuario autenticado: " + username);  // 👈 Imprime el usuario
            return username;
        } else {
            System.out.println("No hay usuario autenticado, valor devuelto: " + principal);
            return "anonymousUser";
        }
    }


    @PostMapping("/")
    public ResponseEntity<Products> addProduct(@RequestBody ProductRequest productRequest) {
        Products product = new Products();
        product.setName(productRequest.getName());
        product.setQuantity(productRequest.getQuantity());
        product.setUnit(productRequest.getUnit());
        product.setCreatedAt(LocalDateTime.now());

        // Si se envió un listId, buscar la lista y asignarla
        if (productRequest.getListId() != null) {
            ProductLists productList = productListRepository.findById(productRequest.getListId())
                    .orElseThrow(() -> new RuntimeException("ProductList not found"));
            product.setList(productList);
        } else {
            product.setList(null);  // Permitir que no tenga lista
        }

        // Guardar el producto en la base de datos
        Products savedProduct = productService.addProduct(product);
        return ResponseEntity.ok(savedProduct);
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

