package br.com.ryoshino.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "listarClientes", url = "http://localhost:8300")
public interface ClienteClient {

    @GetMapping(value = "/listarClientes")
    List<ClienteResponse> listarClientes();
}