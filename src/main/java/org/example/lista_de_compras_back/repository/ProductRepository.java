package org.example.lista_de_compras_back.repository;

import org.example.lista_de_compras_back.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findByListId(Long listId);
}
