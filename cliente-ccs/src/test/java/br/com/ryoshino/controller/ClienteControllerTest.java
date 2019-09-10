package br.com.ryoshino.controller;

import br.com.ryoshino.entity.Cliente;
import br.com.ryoshino.service.ClienteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteController clienteController;

    @Autowired
    private ClienteService clienteService;

    private Cliente cliente;

    @Before
    public void setup() {
        cliente = new Cliente();
        cliente.setNome("rafael");
        cliente.setTelefone(123);
        cliente.setEndereco("teste de endereco");
        cliente.setEmail("rafael@email.com");
        cliente.setCpf(78900000);
        cliente.setDataAtualizacao(LocalDate.now());
        clienteService.salvarCliente(cliente);
    }

    @Test
    public void listarClientes() throws Exception {
        mockMvc.perform(get("/listarClientes")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$[0].nome"), is(cliente.getNome())));
    }
}