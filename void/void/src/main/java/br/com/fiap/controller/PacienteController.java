package br.com.fiap.controller;

import br.com.fiap.model.Paciente;
import br.com.fiap.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    public ResponseEntity<Paciente> criar(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(service.salvar(paciente));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        Optional<Paciente> paciente = service.buscarPorId(id);
        if (paciente.isPresent()) {
            return ResponseEntity.ok(paciente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizar(@PathVariable Long id, @RequestBody Paciente paciente) {
        Paciente atualizado = service.atualizar(id, paciente);
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