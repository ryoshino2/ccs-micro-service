package br.com.ryoshino.service;

import br.com.ryoshino.cliente.ClienteResponse;
import br.com.ryoshino.cliente.ClienteService;
import br.com.ryoshino.model.Conta;
import br.com.ryoshino.repository.ContaRepository;
import br.com.ryoshino.transacao.TransacaoResponse;
import br.com.ryoshino.transacao.TransacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

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


    public Conta buscarConta(Long idConta) {
        return contaRepository.findByIdConta(idConta);
    }

    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    public void atualizarConta(Long idContaCliente) {
        List<TransacaoResponse> transacoes = transacaoService.listarTransacoesParaConsumir(idContaCliente);
        Conta conta = buscarConta(idContaCliente);
        for (TransacaoResponse transacao : transacoes) {
            atualiazarInformacoesDaConta(conta, transacao);
            contaRepository.save(conta);
            transacao.setConsumirTransacao(false);
            transacaoService.alterarStatusTransacao(transacao.getIdTransacao());
        }
    }

    private void atualiazarInformacoesDaConta(Conta conta, TransacaoResponse transacao) {
        conta.setIdConta(conta.getIdConta());
        conta.setSaldo(0.0);
        verificarTipoTransacao(conta, transacao);
        conta.setDataAtualizacao(transacao.getDataTransacao());
    }

    private void verificarTipoTransacao(Conta conta, TransacaoResponse transacao) {
        if (transacao.getTipoTransacao() == "DEBIT") {
            conta.setSaldo(conta.getSaldo() - transacao.getValorTransacao());
        } else {
            conta.setSaldo(conta.getSaldo() + transacao.getValorTransacao());
        }
    }


    public void salvarConta(Conta conta) {
        contaRepository.save(conta);
    }
}
