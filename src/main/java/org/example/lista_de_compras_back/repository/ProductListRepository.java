package org.example.lista_de_compras_back.repository;

import org.example.lista_de_compras_back.model.ProductLists;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductListRepository extends JpaRepository<ProductLists, Long> {
    List<ProductLists> findByUserId(Long userId);
}
