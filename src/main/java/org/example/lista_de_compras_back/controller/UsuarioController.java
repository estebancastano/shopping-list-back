package org.example.lista_de_compras_back.controller;

import org.example.lista_de_compras_back.model.Users;
import org.example.lista_de_compras_back.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody Users users) {
        if (usuarioRepository.findByEmail(users.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("El usuario ya existe");
        }
        usuarioRepository.save(users);
        return ResponseEntity.ok("Usuario registrado con éxito");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody Users users) {
        return usuarioRepository.findByEmail(users.getEmail())
                .map(u -> u.getPassword().equals(users.getPassword()) ?
                        ResponseEntity.ok("Login exitoso") :
                        ResponseEntity.badRequest().body("Credenciales incorrectas"))
                .orElse(ResponseEntity.badRequest().body("Usuario no encontrado"));
    }
}
