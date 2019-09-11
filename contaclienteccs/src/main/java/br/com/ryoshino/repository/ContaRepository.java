package br.com.ryoshino.repository;

import br.com.ryoshino.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    Conta findByIdConta(Long idConta);
}
