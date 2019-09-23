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

    private List<Long> buscarIdsContaClientes() {
        List<Long> contaClienteList = new ArrayList<>();

        for (ContaResponse contaReponse : contaService.listarContas()) {
            contaClienteList.add(contaReponse.getIdConta());
        }
        return contaClienteList;
    }

    public List<Transacao> listarTransacoesParaConsumir(Long idContaCliente) {
        return transacaoRepository.findByConsumirTransacaoAndIdContaCliente(true, idContaCliente);
    }

    private long getIdContaCliente() {
        List<Long> contaReponseList = buscarIdsContaClientes();
        Random gerador = new Random();
        int max = Math.toIntExact(contaReponseList.stream().collect(Collectors.summarizingLong(Long::longValue)).getMax());
        return gerador.nextInt(max) + 1;
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
            e.getStackTrace();
        }
        return transacao;
    }

    private void salvarTransacao(Transacao transacao) {
        transacaoRepository.save(transacao);
    }

    public List<Transacao> buscarTransacoesDoCliente(Long idContaCliente) {
        return transacaoRepository.findByIdContaCliente(idContaCliente);
    }

    public List<Transacao> buscarTodasTransacoes() {
        return transacaoRepository.findAll();
    }

    public void alterarStatusTransacao(Long idTransacao) {
        Transacao transacao = buscarTransacaoPeloId(idTransacao);
        transacao.setConsumirTransacao(false);
        salvarTransacao(transacao);
    }

    private Transacao buscarTransacaoPeloId(Long idTransacao) {
        return transacaoRepository.findByIdTransacao(idTransacao);
    }
}
