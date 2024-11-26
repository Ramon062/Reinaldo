package com.example.reinaldo.Entity;

import org.hibernate.validator.constraints.Length;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Sugestao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "O nome do produto deve ser informado!")
    @Length(min = 5, max = 200, message = "O nome do produto deverá ter de 5 a 200 caracteres")
    private String nome;

    @Column(nullable = false)
    @NotEmpty(message = "A descrição do produto deve ser informada!")
    @Length(min = 10, max = 1000, message = "A descrição do produto deverá ter de 10 a 1000 caracteres")
    private String descricao;

    @Column(nullable = false)
    @NotEmpty(message = "A marca do produto deve ser informada!")
    @Length(min = 3, max = 100, message = "A marca do produto deverá ter de 3 a 100 caracteres")
    private String marca;

    public Sugestao() {
    }

    public Sugestao(String nome, String descricao, String marca) {
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
