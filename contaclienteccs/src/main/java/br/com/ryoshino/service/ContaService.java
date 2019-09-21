package br.com.ryoshino.service;

import br.com.ryoshino.cliente.ClienteResponse;
import br.com.ryoshino.cliente.ClienteService;
import br.com.ryoshino.model.Conta;
import br.com.ryoshino.repository.ContaRepository;
import br.com.ryoshino.transacao.TransacaoResponse;
import br.com.ryoshino.transacao.TransacaoService;
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
    private TransacaoService transacaoService;

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

    public Conta atualizarConta(Long idContaCliente) {

        List<TransacaoResponse> transacoes = transacaoService.listarTransacoes(idContaCliente);
        Conta conta = contaRepository.findByIdConta(transacoes.get(0).getIdContaCliente());
        System.out.println(transacoes);
        System.out.println(conta);
        for (TransacaoResponse transacao : transacoes) {
            conta.setIdConta(conta.getIdConta());
            conta.setSaldo(0.0);
            if (transacao.getTipoTransacao() == "DEBIT") {
                conta.setSaldo(conta.getSaldo() - transacao.getValorTransacao());
            } else {
                conta.setSaldo(conta.getSaldo() + transacao.getValorTransacao());
            }
            conta.setDataAtualizacao(transacao.getDataTransacao());
        }
        return contaRepository.save(conta);
    }
}
