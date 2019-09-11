package br.com.ryoshino.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "listarClientes", url = "http://localhost:8300")
public interface ClienteService {

    @GetMapping(value = "/listarClientes")
    List<ClienteResponse> listarClientes();
}