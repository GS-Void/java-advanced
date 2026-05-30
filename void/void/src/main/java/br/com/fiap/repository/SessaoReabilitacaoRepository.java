package br.com.fiap.repository;

import br.com.fiap.model.SessaoReabilitacao;
import br.com.fiap.model.SessaoReabilitacaoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoReabilitacaoRepository extends JpaRepository<SessaoReabilitacao, SessaoReabilitacaoId> {
}