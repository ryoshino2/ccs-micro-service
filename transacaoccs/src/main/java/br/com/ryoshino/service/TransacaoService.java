package br.com.ryoshino.service;

import br.com.ryoshino.conta.ContaResponse;
import br.com.ryoshino.conta.ContaService;
import br.com.ryoshino.entity.TipoTransacao;
import br.com.ryoshino.entity.Transacao;
import br.com.ryoshino.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaService contaService;

    public List<ContaResponse> listarContas() {
        return contaService.listarContas();
    }

    private boolean verificarSaldo(ContaResponse contaResponse) {
        return contaResponse.getSaldoConta() > 0;
    }

    private List<Long> buscarIdsContaClientes() {
        List<Long> contaClienteList = new ArrayList<>();

        for (ContaResponse contaReponse : contaService.listarContas()) {
            contaClienteList.add(contaReponse.getIdConta());
        }
        return contaClienteList;
    }


    private long getIdContaCliente() {
        List<Long> contaReponseList = buscarIdsContaClientes();
        Random gerador = new Random();
        int max = Math.toIntExact(contaReponseList.stream().collect(Collectors.summarizingLong(Long::longValue)).getMax());
        return max;
    }


    public void atualizarSaldo() {
        Double saldo = null;
        Transacao transacao = gerarTransacao();
        //Consumir do servico de Conta
        ContaResponse contaResponse = contaService.buscarConta(transacao.getIdContaCliente());
        saldo = efetuarTransacao(transacao, contaResponse, saldo);
        atualizarSaldoContaCliente(getIdContaCliente(), transacao, saldo);
//        atualizarDataDeMovimentacaoCliente(contaResponse.getIdCliente(), transacao);
    }


    private Double efetuarTransacao(Transacao transacao, ContaResponse contaResponse, Double saldo) {
        if (transacao.getTipoTransacao() == TipoTransacao.CREDIT) {
            saldo = contaResponse.getSaldoConta() + transacao.getValorTransacao();
        } else if (transacao.getTipoTransacao() == TipoTransacao.DEBIT) {
            saldo = contaResponse.getSaldoConta() - transacao.getValorTransacao();
        }
        return saldo;
    }

    public Transacao gerarTransacao() {
        long id = getIdContaCliente();
        Transacao transacao = null;
        try {
            DecimalFormat formatter = new DecimalFormat("##,###");
            Random gerador = new Random();
            transacao = new Transacao(id, Double.valueOf(formatter.format(gerador.nextDouble() * 100)), LocalDate.now(), TipoTransacao.pegarTransacaoAleatoria());
            salvarTransacao(transacao);
        } catch (NullPointerException e) {
            System.out.println("nao funcionou");
        }
        return transacao;
    }

    private void salvarTransacao(Transacao transacao) {
        transacaoRepository.save(transacao);
    }

//    public void atualizarDataDeMovimentacaoCliente(Long id, Transacao transacao) {
//        Cliente cliente = clienteRepository.findByIdCliente(id);
//        cliente.setDataAtualizacao(transacao.getDataTransacao());
//        clienteRepository.save(cliente);
//    }

    public void atualizarSaldoContaCliente(Long id, Transacao transacao, Double saldo) {
        ContaResponse contaResponse = contaService.buscarConta(id);
        contaResponse.setDataAtualizacao(transacao.getDataTransacao());
        contaResponse.setSaldoConta(saldo);
        contaService.atualizarConta(contaResponse);
    }

    public List<Transacao> buscarTransacoes(Long idContaCliente) {
        return transacaoRepository.findByIdContaCliente(idContaCliente);
    }
}
