package org.example.lista_de_compras_back.repository;

import org.example.lista_de_compras_back.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}
