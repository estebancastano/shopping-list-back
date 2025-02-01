package org.example.lista_de_compras_back.repository;

import org.example.lista_de_compras_back.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findByUserUsername(String username);
}
