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

import br.com.ryoshino.cliente.ClienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author ryoshino
 */

@FeignClient(name = "listarTransacoes", url = "http://localhost:8094")
public interface TransacaoService {

    @GetMapping(value = "/listarTransacoes/{idContaCliente}")
    List<TransacaoResponse> listarTransacoes(@PathVariable("idContaCliente") Long idContaCliente);

    @GetMapping(value = "/listarTransacoesParaConsumir/{idContaCliente}")
    List<TransacaoResponse> listarTransacoesParaConsumir(@PathVariable("idContaCliente") Long idContaCliente);


    @GetMapping(value = "/listarTransacoes")
    List<TransacaoResponse> listarTodasTransacoes();

    @GetMapping(value = "/alterarStatusTransacao/{idTransacao}")
    void alterarStatusTransacao(@PathVariable("idTransacao") Long idTransacao);
}
