package br.com.ryoshino.conta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "listarContas", url = "http://localhost:8090")
public interface ContaService {

    @GetMapping(value = "/listarContas")
    List<ContaResponse> listarContas();

    @GetMapping("/buscarConta/{idConta}")
    ContaResponse buscarConta(@PathVariable("idConta") Long idConta);

    @PostMapping("/atualizarInformacoes")
    ContaResponse atualizarConta(@RequestBody ContaResponse contaResponse);
}