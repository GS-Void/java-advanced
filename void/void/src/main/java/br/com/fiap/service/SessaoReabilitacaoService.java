package br.com.fiap.service;

import br.com.fiap.model.SessaoReabilitacao;
import br.com.fiap.model.SessaoReabilitacaoId;
import br.com.fiap.repository.SessaoReabilitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessaoReabilitacaoService {

    @Autowired
    private SessaoReabilitacaoRepository repository;

    public SessaoReabilitacao salvar(SessaoReabilitacao sessao) {
        return repository.save(sessao);
    }

    public List<SessaoReabilitacao> listarTodas() {
        return repository.findAll();
    }

    public Optional<SessaoReabilitacao> buscarPorId(SessaoReabilitacaoId id) {
        return repository.findById(id);
    }

    public SessaoReabilitacao atualizar(SessaoReabilitacaoId id, SessaoReabilitacao sessaoAtualizada) {
        if (repository.existsById(id)) {
            sessaoAtualizada.setId(id);
            return repository.save(sessaoAtualizada);
        }
        return null;
    }

    public boolean deletar(SessaoReabilitacaoId id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}