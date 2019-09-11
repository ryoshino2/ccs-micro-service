package br.com.ryoshino.cliente;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ClienteResponse {

    private Long idCliente;
    private String nome;
    private String endereco;
    private Integer telefone;
    private String email;
    private Integer cpf;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAtualizacao;


    public Long getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Integer getCpf() {
        return cpf;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    @Override
    public String toString() {
        return "ClienteKafka{" +
                "idCliente=" + idCliente +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone=" + telefone +
                ", email='" + email + '\'' +
                ", cpf=" + cpf +
                '}';
    }

    public ClienteResponse withIdCliente(Long idCliente) {
        this.idCliente = idCliente;
        return this;
    }


}
