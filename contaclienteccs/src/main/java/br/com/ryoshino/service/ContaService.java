package br.com.ryoshino.service;

import br.com.ryoshino.cliente.ClienteResponse;
import br.com.ryoshino.cliente.ClienteService;
import br.com.ryoshino.model.Conta;
import br.com.ryoshino.repository.ContaRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class ContaService {
    //    private final String topic = "cliente_ccs";
//    private final CcsKafka kafkaProperties = new CcsKafka();
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private RestTemplate restTemplate;

    public void gerarConta() {
        Logger logger = LoggerFactory.getLogger(ContaService.class.getName());

        for (ClienteResponse clienteResponse : clienteService.listarClientes()) {
            Conta conta = new Conta();
            conta.setIdCliente(clienteResponse.getIdCliente());
            conta.setDataAtualizacao(LocalDate.now());
            conta.setSaldo(0.0);
            contaRepository.save(conta);
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @HystrixCommand(fallbackMethod = "erro")
    public List readingList() {
        URI uri = URI.create("http://localhost:8300/listarClientes");

        return this.restTemplate.getForObject(uri, List.class);
    }

    public List erro() {
        return Collections.emptyList();
    }

    public Conta buscarConta(Long idConta) {
        return contaRepository.findByIdConta(idConta);
    }

    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    public Conta atualizarConta(Conta conta) {
        conta.setIdConta(conta.getIdConta());
        conta.setSaldo(conta.getSaldo());
        conta.setDataAtualizacao(conta.getDataAtualizacao());
        return contaRepository.save(conta);
    }
}
