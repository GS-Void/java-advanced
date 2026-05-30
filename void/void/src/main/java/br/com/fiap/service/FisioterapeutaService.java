package br.com.fiap.service;

import br.com.fiap.model.Fisioterapeuta;
import br.com.fiap.repository.FisioterapeutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FisioterapeutaService {

    @Autowired
    private FisioterapeutaRepository repository;

    public Fisioterapeuta salvar(Fisioterapeuta fisioterapeuta) {
        return repository.save(fisioterapeuta);
    }

    public List<Fisioterapeuta> listarTodos() {
        return repository.findAll();
    }

    public Optional<Fisioterapeuta> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Fisioterapeuta atualizar(Long id, Fisioterapeuta fisioterapeutaAtualizado) {
        if (repository.existsById(id)) {
            fisioterapeutaAtualizado.setId(id);
            return repository.save(fisioterapeutaAtualizado);
        }
        return null;
    }

    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}