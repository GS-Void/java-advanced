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

    @Operation(
            summary = "Cadastrar um novo paciente",
            description = "Cria um novo registro de paciente no sistema. Como fazer a requisição: Envie um JSON no corpo (body) contendo nome, cpf, email e o limite de esforço crítico (número positivo)."
    )
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> criar(@Valid @RequestBody PacienteRequestDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setEmail(dto.getEmail());
        paciente.setLimiteEsforcoCritico(dto.getLimiteEsforcoCritico());

        Paciente salvo = service.salvar(paciente);
        return ResponseEntity.ok(converterParaResponse(salvo));
    }

    @Operation(
            summary = "Listar todos os pacientes",
            description = "Retorna uma lista contendo todos os pacientes cadastrados no banco de dados Oracle. Como fazer a requisição: Basta executar um GET nesta rota, não exige parâmetros."
    )
    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listarTodos() {
        List<Paciente> pacientes = service.listarTodos();
        List<PacienteResponseDTO> responses = new ArrayList<>();

        for (Paciente p : pacientes) {
            responses.add(converterParaResponse(p));
        }

        return ResponseEntity.ok(responses);
    }

    @Operation(
            summary = "Buscar paciente por ID",
            description = "Busca um paciente específico utilizando o seu código identificador (ID). Como fazer a requisição: Passe o ID numérico do paciente diretamente na URL (ex: /api/pacientes/1)."
    )
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<Paciente> paciente = service.buscarPorId(id);

        if (paciente.isPresent()) {
            return ResponseEntity.ok(converterParaResponse(paciente.get()));
        }
        return ResponseEntity.notFound().build();
    }

    private PacienteResponseDTO converterParaResponse(Paciente paciente) {
        PacienteResponseDTO response = new PacienteResponseDTO();
        response.setId(paciente.getId());
        response.setNome(paciente.getNome());
        response.setLimiteEsforcoCritico(paciente.getLimiteEsforcoCritico());
        return response;
    }
}