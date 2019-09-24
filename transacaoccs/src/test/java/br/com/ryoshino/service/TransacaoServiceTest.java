package br.com.ryoshino.service;

import br.com.ryoshino.conta.ContaResponse;
import br.com.ryoshino.conta.ContaService;
import br.com.ryoshino.entity.Transacao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * @author ryoshino
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransacaoServiceTest {

    @Autowired
    private TransacaoService transacaoService;

    @MockBean
    private ContaService contaService;

    private ContaResponse contaResponse;
    private Transacao transacao;

    @Before
    public void setup() {
        List<ContaResponse> listaConta = new ArrayList<>();
        contaResponse = new ContaResponse();
        contaResponse.setIdCliente(1L);
        contaResponse.setDataAtualizacao(LocalDate.now());
        contaResponse.setIdConta(1l);
        contaResponse.setSaldoConta(0.0);
        listaConta.add(contaResponse);
        when(contaService.listarContas()).thenReturn(listaConta);
        transacao = transacaoService.gerarTransacao();
    }

    @Test
    public void testListarTransacoesParaConsumir() {
        assertTrue(transacao.isConsumirTransacao());
    }

    @Test
    public void testGerarTransacao() {
        assertNotNull(transacao);
        assertEquals(transacao.getDataTransacao(), LocalDate.now());
        assertEquals(transacao.getIdContaCliente(), contaResponse.getIdConta());
        assertTrue(transacao.isConsumirTransacao());
    }

    @Test
    public void testBuscarTransacoesDoCliente() {
        List<Transacao> transacaoList = transacaoService.buscarTransacoesDoCliente(contaResponse.getIdConta());
        assertNotEquals(0, transacaoList.size());
    }

    @Test
    public void testBuscarTodasTransacoes() {
        assertEquals(1, transacaoService.buscarTodasTransacoes().size());
    }

    @Test
    public void testAlterarStatusTransacao() {
        transacao = transacaoService.alterarStatusTransacao(transacao.getIdTransacao());
        assertFalse(transacao.isConsumirTransacao());
    }
}