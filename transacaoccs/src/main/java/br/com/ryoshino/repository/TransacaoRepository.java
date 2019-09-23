package br.com.ryoshino.repository;

import br.com.ryoshino.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByIdContaCliente(Long idContaCliente);
    List<Transacao> findByConsumirTransacaoAndIdContaCliente(Boolean consumirTransacao, Long idContaCliente);
    Transacao findByIdTransacao(Long idTransacao);
}
