package com.example.reinaldo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.reinaldo.Entity.Usuario;
import com.example.reinaldo.Repository.UsuarioRepository;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Cadastro de Usuário (Create)
    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = Optional.ofNullable(usuarioRepository.findByEmail(usuario.getEmail()));
        if (usuarioExistente.isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado!");
        }

        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }

    // Login de Usuário (Login)
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente == null || !usuarioExistente.getSenha().equals(usuario.getSenha())) {
            return ResponseEntity.badRequest().body("Credenciais inválidas!");
        }

        return ResponseEntity.ok("Login efetuado com sucesso!");
    }

    // Buscar todos os usuários (Read)
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscar usuário por ID (Read)
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar usuário (Update)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (!usuarioExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        usuario.setId(id);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuário atualizado com sucesso!");
    }

    // Deletar usuário (Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (!usuarioExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        usuarioRepository.deleteById(id);
        return ResponseEntity.ok("Usuário deletado com sucesso!");
    }
}