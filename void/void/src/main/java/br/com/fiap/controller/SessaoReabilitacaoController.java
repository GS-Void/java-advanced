package br.com.fiap.controller;

import br.com.fiap.dto.SessaoReabilitacaoRequestDTO;
import br.com.fiap.dto.SessaoReabilitacaoResponseDTO;
import br.com.fiap.model.Paciente;
import br.com.fiap.model.SessaoReabilitacao;
import br.com.fiap.model.SessaoReabilitacaoId;
import br.com.fiap.service.SessaoReabilitacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sessoes")
@Tag(name = "Sessões de Reabilitação", description = "Endpoints para registro de telemetria e desgaste físico dos pacientes")
public class SessaoReabilitacaoController {

    @Autowired
    private SessaoReabilitacaoService service;

    @Operation(summary = "Registrar nova sessão de telemetria")
    @PostMapping
    public ResponseEntity<SessaoReabilitacaoResponseDTO> criar(@Valid @RequestBody SessaoReabilitacaoRequestDTO dto) {
        SessaoReabilitacaoId id = new SessaoReabilitacaoId();
        id.setPacienteId(dto.pacienteId());
        id.setDataSessao(dto.dataSessao());

        SessaoReabilitacao sessao = new SessaoReabilitacao();
        sessao.setId(id);
        sessao.setDesgasteAcumulado(dto.desgasteAcumulado());
        sessao.setAlertaFadigaCritica(false);

        Paciente paciente = new Paciente();
        paciente.setId(dto.pacienteId());
        sessao.setPaciente(paciente);

        SessaoReabilitacao salva = service.salvar(sessao);
        return ResponseEntity.ok(converterParaResponse(salva));
    }

    @Operation(summary = "Listar histórico de sessões")
    @GetMapping
    public ResponseEntity<List<SessaoReabilitacaoResponseDTO>> listarTodas() {
        List<SessaoReabilitacao> lista = service.listarTodas();
        List<SessaoReabilitacaoResponseDTO> responses = new ArrayList<>();

        for (SessaoReabilitacao s : lista) {
            responses.add(converterParaResponse(s));
        }
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Buscar sessão específica")
    @GetMapping("/{pacienteId}/{dataSessao}")
    public ResponseEntity<SessaoReabilitacaoResponseDTO> buscarPorId(
            @PathVariable Long pacienteId,
            @PathVariable LocalDate dataSessao) {

        SessaoReabilitacaoId id = new SessaoReabilitacaoId();
        id.setPacienteId(pacienteId);
        id.setDataSessao(dataSessao);

        Optional<SessaoReabilitacao> sessao = service.buscarPorId(id);
        if (sessao.isPresent()) {
            return ResponseEntity.ok(converterParaResponse(sessao.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualizar sessão de telemetria")
    @PutMapping("/{pacienteId}/{dataSessao}")
    public ResponseEntity<SessaoReabilitacaoResponseDTO> atualizar(
            @PathVariable Long pacienteId,
            @PathVariable LocalDate dataSessao,
            @Valid @RequestBody SessaoReabilitacaoRequestDTO dto) {

        SessaoReabilitacaoId id = new SessaoReabilitacaoId();
        id.setPacienteId(pacienteId);
        id.setDataSessao(dataSessao);

        SessaoReabilitacao sessao = new SessaoReabilitacao();
        sessao.setId(id);
        sessao.setDesgasteAcumulado(dto.desgasteAcumulado());
        sessao.setAlertaFadigaCritica(false);

        Paciente paciente = new Paciente();
        paciente.setId(dto.pacienteId());
        sessao.setPaciente(paciente);

        SessaoReabilitacao atualizada = service.atualizar(id, sessao);
        if (atualizada != null) {
            return ResponseEntity.ok(converterParaResponse(atualizada));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Deletar sessão")
    @DeleteMapping("/{pacienteId}/{dataSessao}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long pacienteId,
            @PathVariable LocalDate dataSessao) {

        SessaoReabilitacaoId id = new SessaoReabilitacaoId();
        id.setPacienteId(pacienteId);
        id.setDataSessao(dataSessao);

        if (service.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private SessaoReabilitacaoResponseDTO converterParaResponse(SessaoReabilitacao sessao) {
        return new SessaoReabilitacaoResponseDTO(
                sessao.getId(),
                sessao.getDesgasteAcumulado(),
                sessao.getAlertaFadigaCritica()
        );
    }
}