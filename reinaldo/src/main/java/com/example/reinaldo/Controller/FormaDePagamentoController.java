package com.example.reinaldo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reinaldo.Entity.FormaDePagamento;
import com.example.reinaldo.Repository.FormaDePagamentoRepository;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaDePagamentoController {

    @Autowired
    private FormaDePagamentoRepository repository;

    @PostMapping
    public ResponseEntity<FormaDePagamento> criarFormaDePagamento(@Validated @RequestBody FormaDePagamento formaDePagamento) {
        FormaDePagamento novaForma = repository.save(formaDePagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaForma);
    }

    @GetMapping
    public ResponseEntity<List<FormaDePagamento>> listarFormasDePagamento() {
        List<FormaDePagamento> formas = repository.findAll();
        return ResponseEntity.ok(formas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaDePagamento> buscarFormaDePagamento(@PathVariable Long id) {
        Optional<FormaDePagamento> forma = repository.findById(id);
        return forma.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaDePagamento> atualizarFormaDePagamento(@PathVariable Long id, @Validated @RequestBody FormaDePagamento formaAtualizada) {
        return repository.findById(id)
                .map(forma -> {
                    forma.setNumeroCartao(formaAtualizada.getNumeroCartao());
                    forma.setDataExpedicao(formaAtualizada.getDataExpedicao());
                    forma.setCodigoSeguranca(formaAtualizada.getCodigoSeguranca());
                    forma.setBandeira(formaAtualizada.getBandeira());
                    FormaDePagamento atualizada = repository.save(forma);
                    return ResponseEntity.ok(atualizada);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFormaDePagamento(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}