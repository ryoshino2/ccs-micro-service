/*
 * Copyright (c) - UOL Inc, Todos os direitos reservados
 *
 * Este arquivo e uma propriedade confidencial do Universo Online Inc. Nenhuma parte do mesmo pode
 * ser copiada, reproduzida, impressa ou transmitida por qualquer meio sem autorizacao expressa e
 * por escrito de um representante legal do Universo Online Inc.
 *
 * All rights reserved
 *
 * This file is a confidential property of Universo Online Inc. No part of this file may be
 * reproduced or copied in any form or by any means without written permisson from an authorized
 * person from Universo Online Inc.
 */
package br.com.ryoshino.transacao;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author ryoshino
 */


public class TransacaoResponse {
    private Long idTransacao;
    private Long idContaCliente;
    private Double valorTransacao;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataTransacao;
    private String tipoTransacao;
    private boolean consumirTransacao;

    public Long getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Long idTransacao) {
        this.idTransacao = idTransacao;
    }

    public Long getIdContaCliente() {
        return idContaCliente;
    }

    public void setIdContaCliente(Long idContaCliente) {
        this.idContaCliente = idContaCliente;
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

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    @Override
    public String toString() {
        return "TransacaoResponse{" +
                "idTransacao=" + idTransacao +
                ", idContaCliente=" + idContaCliente +
                ", valorTransacao=" + valorTransacao +
                ", dataTransacao=" + dataTransacao +
                ", tipoTransacao='" + tipoTransacao + '\'' +
                '}';
    }

    public boolean isConsumirTransacao() {
        return consumirTransacao;
    }

    public void setConsumirTransacao(boolean consumirTransacao) {
        this.consumirTransacao = consumirTransacao;
    }
}
