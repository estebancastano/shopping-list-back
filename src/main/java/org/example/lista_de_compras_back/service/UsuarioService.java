package org.example.lista_de_compras_back.service;

import org.example.lista_de_compras_back.model.Users;
import org.example.lista_de_compras_back.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Método para registrar un nuevo usuario
    public Users registrarUsuario(Users users) {
        if (usuarioRepository.findByEmail(users.getEmail()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }
        users.setPassword(passwordEncoder.encode(users.getPassword())); // Encriptar la contraseña
        return usuarioRepository.save(users);
    }

    // Método para autenticar un usuario (ejemplo de inicio de sesión)
    public Optional<Users> autenticarUsuario(String email, String contrasena) {
        Optional<Users> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent() && passwordEncoder.matches(contrasena, usuario.get().getPassword())) {
            return usuario;
        }
        return Optional.empty();
    }
}
