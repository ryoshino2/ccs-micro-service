package br.com.ryoshino.service;

import br.com.ryoshino.cliente.ClienteService;
import br.com.ryoshino.model.Conta;
import br.com.ryoshino.repository.ContaRepository;
import br.com.ryoshino.transacao.TransacaoResponse;
import br.com.ryoshino.transacao.TransacaoService;
import org.apache.tomcat.jni.Local;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @author ryoshino
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContaServiceTest {


    @Autowired
    private ContaService contaService;

    @MockBean
    private TransacaoService transacaoService;

    @MockBean
    private ContaRepository contaRepository;

    @Test
    public void atualizarConta() {
        Conta conta = new Conta(1L, 0.0, LocalDate.now());

        List<TransacaoResponse> transacoes = new ArrayList<>();
        TransacaoResponse transacao = new TransacaoResponse();

        transacao.setTipoTransacao("CREDIT");
        transacao.setIdTransacao(1L);
        transacao.setValorTransacao(10.0);
        transacao.setConsumirTransacao(true);
        transacao.setIdContaCliente(1L);
        transacao.setDataTransacao(LocalDate.now());

        transacoes.add(transacao);

        when(transacaoService.listarTransacoesParaConsumir(any())).thenReturn(transacoes);
        when(contaRepository.findByIdConta(1L)).thenReturn(conta);
        contaService.atualizarConta(1L);
        assertEquals(LocalDate.now(), conta.getDataAtualizacao());
        assertEquals(10.0, conta.getSaldo(), 0.0);
    }
}