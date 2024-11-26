package com.example.reinaldo.Controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reinaldo.Entity.Sugestao;
import com.example.reinaldo.Repository.SugestaoRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sugestoes")
public class SugestaoController {

    @Autowired
    private SugestaoRepository sugestaoRepository;

    // Criar uma nova sugestão
    @PostMapping
    public ResponseEntity<?> criarSugestao(@Valid @RequestBody Sugestao sugestao) {
        try {
            Sugestao novaSugestao = sugestaoRepository.save(sugestao);
            return ResponseEntity.ok(novaSugestao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar a sugestão: " + e.getMessage());
        }
    }

    // Listar todas as sugestões
    @GetMapping
    public ResponseEntity<?> listarSugestoes() {
        try {
            return ResponseEntity.ok(sugestaoRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao listar sugestões: " + e.getMessage());
        }
    }

    // Atualizar uma sugestão existente
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarSugestao(@PathVariable Long id, @Valid @RequestBody Sugestao sugestao) {
        try {
            Optional<Sugestao> sugestaoExistente = sugestaoRepository.findById(id);
            if (!sugestaoExistente.isPresent()) {
                return ResponseEntity.status(404).body("Sugestão não encontrada.");
            }

            Sugestao sugestaoAtualizada = sugestaoExistente.get();
            sugestaoAtualizada.setNome(sugestao.getNome());
            sugestaoAtualizada.setDescricao(sugestao.getDescricao());
            sugestaoAtualizada.setMarca(sugestao.getMarca());

            Sugestao savedSugestao = sugestaoRepository.save(sugestaoAtualizada);
            return ResponseEntity.ok(savedSugestao);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar a sugestão: " + e.getMessage());
        }
    }

    // Deletar uma sugestão
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarSugestao(@PathVariable Long id) {
        try {
            Optional<Sugestao> sugestaoExistente = sugestaoRepository.findById(id);
            if (!sugestaoExistente.isPresent()) {
                return ResponseEntity.status(404).body("Sugestão não encontrada.");
            }

            sugestaoRepository.deleteById(id);
            return ResponseEntity.ok("Sugestão deletada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar a sugestão: " + e.getMessage());
        }
    }
}
