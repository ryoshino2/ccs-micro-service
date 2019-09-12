package br.com.ryoshino.controller;

import br.com.ryoshino.cliente.ClienteResponse;
import br.com.ryoshino.cliente.ClienteService;
import br.com.ryoshino.model.Conta;
import br.com.ryoshino.repository.ContaRepository;
import br.com.ryoshino.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/gerarConta")
    public void gerarConta() {
        contaService.gerarConta();
    }

//    @GetMapping("/listarContas")
//    public List listarContas() {
//        return contaService.readingList();
//    }

    @GetMapping("/listarClientes")
    public List<ClienteResponse> listarClientes() {
        List clientes = null;
        try {
            clientes = clienteService.listarClientes();
        } catch (Exception e) {
            e.getCause();
            clientes = Collections.emptyList();
        }
        return clientes;
    }

    @GetMapping("/buscarConta/{idConta}")
    public Conta buscarConta(@PathVariable("idConta") Long idConta) throws IOException {
        return contaService.buscarConta(idConta);
    }

    @GetMapping("/listarContas")
    public List<Conta> listarContas(){
        return contaService.listarContas();
    }

    @PostMapping("/atualizarInformacoes")
    public Conta criarConta(@RequestBody Conta conta){
        return contaService.atualizarConta(conta);
    }
}
