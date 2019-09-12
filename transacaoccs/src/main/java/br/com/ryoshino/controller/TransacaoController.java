package br.com.ryoshino.controller;

import br.com.ryoshino.conta.ContaResponse;
import br.com.ryoshino.conta.ContaService;
import br.com.ryoshino.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private ContaService contaService;

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
    public void gerarTransacao() {
        transacaoService.gerarTransacao();
    }

    @PostMapping("/atualizarInformacoes")
    public void atualizarInformacoes(@RequestBody ContaResponse contaResponse) {
        contaService.atualizarConta(contaResponse);
    }
}
