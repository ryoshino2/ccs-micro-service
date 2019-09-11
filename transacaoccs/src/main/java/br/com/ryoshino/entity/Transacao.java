package br.com.ryoshino.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Transacao {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTransacao;

    private Long idContaCliente;
    private Double valorTransacao;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataTransacao;
    private TipoTransacao tipoTransacao;


    public Transacao() {
    }

    public Transacao(Long idContaCliente, Double valorTransacao, LocalDate dataTransacao, TipoTransacao tipoTransacao) {
        this.idContaCliente = idContaCliente;
        this.valorTransacao = valorTransacao;
        this.dataTransacao = dataTransacao;
        this.tipoTransacao = tipoTransacao;
    }


    public Long getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Long idTransacao) {
        this.idTransacao = idTransacao;
    }

    public Double getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(Double valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDate dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }


    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + idTransacao +
                ", valorTransacao=" + valorTransacao +
                ", dataTransacao=" + dataTransacao +
                ", tipoTransacao=" + tipoTransacao +
                '}';
    }

    public Long getIdContaCliente() {
        return idContaCliente;
    }

    public void setIdContaCliente(Long idContaCliente) {
        this.idContaCliente = idContaCliente;
    }
}
