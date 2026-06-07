package br.com.fiap.controller;

import br.com.fiap.dto.PacienteRequestDTO;
import br.com.fiap.dto.PacienteResponseDTO;
import br.com.fiap.model.Paciente;
import br.com.fiap.service.PacienteService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
@Tag(name = "Pacientes", description = "Endpoints para gerenciamento de pacientes no projeto VOID")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @Operation(summary = "Cadastrar um novo paciente")
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> criar(@Valid @RequestBody PacienteRequestDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setNome(dto.nome());
        paciente.setCpf(dto.cpf());
        paciente.setEmail(dto.email());
        paciente.setLimiteEsforcoCritico(dto.limiteEsforcoCritico());

        Paciente salvo = service.salvar(paciente);
        return ResponseEntity.ok(converterParaResponse(salvo));
    }

    @Operation(summary = "Listar todos os pacientes")
    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listarTodos() {
        List<Paciente> pacientes = service.listarTodos();
        List<PacienteResponseDTO> responses = new ArrayList<>();

        for (Paciente p : pacientes) {
            responses.add(converterParaResponse(p));
        }

        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Buscar paciente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<Paciente> paciente = service.buscarPorId(id);

        if (paciente.isPresent()) {
            return ResponseEntity.ok(converterParaResponse(paciente.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualizar paciente")
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PacienteRequestDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setNome(dto.nome());
        paciente.setCpf(dto.cpf());
        paciente.setEmail(dto.email());
        paciente.setLimiteEsforcoCritico(dto.limiteEsforcoCritico());

        Paciente atualizado = service.atualizar(id, paciente);
        if (atualizado != null) {
            return ResponseEntity.ok(converterParaResponse(atualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Deletar paciente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private PacienteResponseDTO converterParaResponse(Paciente paciente) {
        return new PacienteResponseDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getLimiteEsforcoCritico()
        );
    }
}