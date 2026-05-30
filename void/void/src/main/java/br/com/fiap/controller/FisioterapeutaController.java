package br.com.fiap.controller;

import br.com.fiap.model.Fisioterapeuta;
import br.com.fiap.service.FisioterapeutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fisioterapeutas")
public class FisioterapeutaController {

    @Autowired
    private FisioterapeutaService service;

    @PostMapping
    public ResponseEntity<Fisioterapeuta> criar(@RequestBody Fisioterapeuta fisioterapeuta) {
        return ResponseEntity.ok(service.salvar(fisioterapeuta));
    }

    @GetMapping
    public ResponseEntity<List<Fisioterapeuta>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fisioterapeuta> buscarPorId(@PathVariable Long id) {
        Optional<Fisioterapeuta> fisioterapeuta = service.buscarPorId(id);
        if (fisioterapeuta.isPresent()) {
            return ResponseEntity.ok(fisioterapeuta.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fisioterapeuta> atualizar(@PathVariable Long id, @RequestBody Fisioterapeuta fisioterapeuta) {
        Fisioterapeuta atualizado = service.atualizar(id, fisioterapeuta);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}