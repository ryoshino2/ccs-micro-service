package br.com.ryoshino.controller;

import br.com.ryoshino.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@RestController("/conta")
public class ContaController {


    @Autowired
    private ContaService contaService;

    @Autowired
    private ClienteClient clienteClient;

    @GetMapping("/gerarConta")
    public void gerarConta() {
        contaService.gerarConta();
    }

//    @GetMapping("/listarContas")
//    public LinkedHashMap listarContas() {
//        return contaService.readingList();
//    }

    @GetMapping("/listarClientes")
    public List<ClienteResponse> listarClientes(){
        return clienteClient.listarClientes();
    }
}
