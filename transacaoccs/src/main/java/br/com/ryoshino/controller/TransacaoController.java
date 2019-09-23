package br.com.ryoshino.controller;

import br.com.ryoshino.conta.ContaResponse;
import br.com.ryoshino.entity.Transacao;
import br.com.ryoshino.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;


    @GetMapping("/listarContas")
    public List<ContaResponse> listarContas() {
        List contas = null;
        try {
            contas = transacaoService.listarContas();
        } catch (Exception e) {
            e.getCause();
            contas = Collections.emptyList();
        }
        return contas;
    }

    @GetMapping("/gerarTransacao")
    public Transacao gerarTransacao() {
        return  transacaoService.gerarTransacao();
    }

    @GetMapping("/listarTransacoes/{idContaCliente}")
    public List<Transacao> listarTransacoesDaConta(@PathVariable ("idContaCliente") Long idContaCliente){
        return transacaoService.buscarTransacoes(idContaCliente);
    }

    @GetMapping("/listarTransacoesParaConsumir/{idContaCliente}")
    public List<Transacao> listarTransacoesParaConsumir(@PathVariable ("idContaCliente") Long idContaCliente){
        return transacaoService.listarTransacoesParaConsumir(idContaCliente);
    }

    @GetMapping("/listarTodasTransacoes")
    public List<Transacao> listarTodasTransacoes(){
        return transacaoService.buscarTodasTransacoes();
    }

    @GetMapping(value = "/alterarStatusTransacao/{idTransacao}")
    public void alterarStatusTransacao(@PathVariable ("idTransacao") Long idTransacao){
        transacaoService.alterarStatusTransacao(idTransacao);
    }
}
