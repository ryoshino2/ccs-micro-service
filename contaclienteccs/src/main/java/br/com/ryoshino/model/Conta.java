package br.com.ryoshino.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;

@Entity
public class Conta {

    @Id
    @GeneratedValue
    private Long idConta;

    private Long idCliente;

    private Double saldo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAtualizacao;


    public Conta() {
    }

    public Conta(Long idCliente, Double saldo, LocalDate dataAtualizacao) {
        this.idCliente = idCliente;
        this.saldo = saldo;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public String toString() {
        return "ContaCliente{" +
                "idConta=" + idConta +
                ", idCliente=" + idCliente +
                ", saldoConta=" + saldo +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }

}
