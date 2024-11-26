package com.example.reinaldo.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class FormaDePagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "O número do cartão deve ser informado!")
    @Pattern(regexp = "\\d{16}", message = "O número do cartão deve ter 16 dígitos")
    private String numeroCartao;

    @NotEmpty(message = "A data de expedição deve ser informada!")
    @Pattern(regexp = "\\d{2}/\\d{4}", message = "A data de expedição deve estar no formato MM/AAAA")
    private String dataExpedicao;

    @NotEmpty(message = "O código de segurança deve ser informado!")
    @Pattern(regexp = "\\d{3}", message = "O código de segurança deve ter 3 dígitos")
    private String codigoSeguranca;

    @NotNull(message = "A bandeira deve ser informada!") // Alterado para @NotNull
    @Enumerated(EnumType.STRING)
    private Bandeira bandeira;

    public enum Bandeira {
        VISA, AMERICAN_EXPRESS, MASTERCARD, ELO, HIPERCARD
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getDataExpedicao() {
        return dataExpedicao;
    }

    public void setDataExpedicao(String dataExpedicao) {
        this.dataExpedicao = dataExpedicao;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public Bandeira getBandeira() {
        return bandeira;
    }

    public void setBandeira(Bandeira bandeira) {
        this.bandeira = bandeira;
    }
}
