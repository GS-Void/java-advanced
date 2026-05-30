package br.com.fiap.controller;

import br.com.fiap.dto.PacienteRequestDTO;
import br.com.fiap.dto.PacienteResponseDTO;
import br.com.fiap.model.Paciente;
import br.com.fiap.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> criar(@Valid @RequestBody PacienteRequestDTO dto) {
        // Converte o DTO de entrada para a Entidade
        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setEmail(dto.getEmail());
        paciente.setLimiteEsforcoCritico(dto.getLimiteEsforcoCritico());

        // Salva no banco via Service
        Paciente salvo = service.salvar(paciente);

        // Converte a Entidade salva para o DTO de saída
        PacienteResponseDTO response = converterParaResponse(salvo);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listarTodos() {
        List<Paciente> pacientes = service.listarTodos();
        List<PacienteResponseDTO> responses = new ArrayList<>();

        for (Paciente p : pacientes) {
            responses.add(converterParaResponse(p));
        }

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<Paciente> paciente = service.buscarPorId(id);

        if (paciente.isPresent()) {
            PacienteResponseDTO response = converterParaResponse(paciente.get());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    // Método auxiliar para não repetir código de conversão
    private PacienteResponseDTO converterParaResponse(Paciente paciente) {
        PacienteResponseDTO response = new PacienteResponseDTO();
        response.setId(paciente.getId());
        response.setNome(paciente.getNome());
        response.setLimiteEsforcoCritico(paciente.getLimiteEsforcoCritico());
        return response;
    }
}