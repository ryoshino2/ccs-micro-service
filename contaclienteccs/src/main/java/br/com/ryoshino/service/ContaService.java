package br.com.ryoshino.service;

import br.com.ryoshino.controller.ClienteClient;
import br.com.ryoshino.controller.ClienteResponse;
import br.com.ryoshino.model.Conta;
import br.com.ryoshino.repository.ContaRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.*;

@Service
public class ContaService {
    //    private final String topic = "cliente_ccs";
//    private final CcsKafka kafkaProperties = new CcsKafka();
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ClienteClient clienteClient;

    @Autowired
    private RestTemplate restTemplate;
    private Map<String, Object> metadata;

    public void gerarConta() {
        Logger logger = LoggerFactory.getLogger(ContaService.class.getName());



        for (ClienteResponse clienteResponse : clienteClient.listarClientes()) {

//            Map<String, Object> stringobjectMap = new HashMap<String, Object>();
//
//            stringobjectMap.put("idCliente",clienteResponse.getIdCliente());
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
    public LinkedHashMap readingList() {
        URI uri = URI.create("http://localhost:8300/listarClientes");

        return this.restTemplate.getForObject(uri, LinkedHashMap.class);
    }


    public List<Object> erro() {
        return Collections.emptyList();
    }
}
