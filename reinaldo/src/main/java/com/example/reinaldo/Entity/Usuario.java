package com.example.reinaldo.Entity;


import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "O nome deve ser informado!")
    @Length(min = 5, max = 200, message = "O nome deverá ter de 5 a 200 caracteres")
    private String nome;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "O email deve ser informado!")
    @Length(min = 5, max = 300, message = "O email deverá ter de 5 a 300 caracteres")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "A senha deve ser informada!")
    @Length(min = 4, max = 30, message = "A senha deverá ter de 4 a 30 caracteres")
    private String senha;

    @NotEmpty(message = "A cidade deve ser informada!")
    @Length(min = 2, max = 100, message = "A cidade deverá ter de 2 a 100 caracteres")
    private String cidade;

    @NotEmpty(message = "O estado deve ser informado!")
    @Length(min = 2, max = 100, message = "O estado deverá ter de 2 a 100 caracteres")
    private String estado;

    @NotEmpty(message = "O CEP deve ser informado!")
    @Length(min = 8, max = 8, message = "O CEP deverá ter 8 caracteres")
    private String cep;

    private String complemento;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, String cidade, String estado, String cep, String complemento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.complemento = complemento;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

   
}