package com.example.reinaldo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.reinaldo.Entity.FormaDePagamento;

public interface FormaDePagamentoRepository extends JpaRepository<FormaDePagamento, Long> {
}