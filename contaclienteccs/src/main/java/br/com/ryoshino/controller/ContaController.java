package br.com.ryoshino.controller;

import br.com.ryoshino.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

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
}
