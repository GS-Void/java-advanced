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

    @Operation(
            summary = "Registrar nova sessão de telemetria",
            description = "Salva os dados de desgaste captados pelos sensores IoT. Como fazer a requisição: Envie no corpo um JSON com pacienteId, dataSessao (formato YYYY-MM-DD) e desgasteAcumulado."
    )
    @PostMapping
    public ResponseEntity<SessaoReabilitacaoResponseDTO> criar(@Valid @RequestBody SessaoReabilitacaoRequestDTO dto) {
        SessaoReabilitacaoId id = new SessaoReabilitacaoId();
        id.setPacienteId(dto.getPacienteId());
        id.setDataSessao(dto.getDataSessao());

        SessaoReabilitacao sessao = new SessaoReabilitacao();
        sessao.setId(id);
        sessao.setDesgasteAcumulado(dto.getDesgasteAcumulado());
        sessao.setAlertaFadigaCritica(false);

        Paciente paciente = new Paciente();
        paciente.setId(dto.getPacienteId());
        sessao.setPaciente(paciente);

        SessaoReabilitacao salva = service.salvar(sessao);
        return ResponseEntity.ok(converterParaResponse(salva));
    }

    @Operation(
            summary = "Listar histórico de sessões",
            description = "Retorna todas as sessões registradas no banco. Como fazer a requisição: GET simples na rota base."
    )
    @GetMapping
    public ResponseEntity<List<SessaoReabilitacaoResponseDTO>> listarTodas() {
        List<SessaoReabilitacao> lista = service.listarTodas();
        List<SessaoReabilitacaoResponseDTO> responses = new ArrayList<>();

        for (SessaoReabilitacao s : lista) {
            responses.add(converterParaResponse(s));
        }
        return ResponseEntity.ok(responses);
    }

    @Operation(
            summary = "Buscar sessão específica",
            description = "Localiza uma sessão usando a chave composta (ID do Paciente + Data). Como fazer a requisição: GET passando os dois parâmetros na URL (ex: /api/sessoes/1/2026-04-15)."
    )
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

    @Operation(
            summary = "Atualizar sessão de telemetria",
            description = "Substitui os dados de uma sessão existente. Como fazer a requisição: Passe pacienteId e dataSessao na URL e envie os novos dados no corpo do JSON."
    )
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
        sessao.setDesgasteAcumulado(dto.getDesgasteAcumulado());
        sessao.setAlertaFadigaCritica(false);

        Paciente paciente = new Paciente();
        paciente.setId(dto.getPacienteId());
        sessao.setPaciente(paciente);

        SessaoReabilitacao atualizada = service.atualizar(id, sessao);
        if (atualizada != null) {
            return ResponseEntity.ok(converterParaResponse(atualizada));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Deletar sessão",
            description = "Remove o registro de uma sessão utilizando a chave composta. Como fazer a requisição: DELETE passando pacienteId e dataSessao na URL."
    )
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
        SessaoReabilitacaoResponseDTO response = new SessaoReabilitacaoResponseDTO();
        response.setId(sessao.getId());
        response.setDesgasteAcumulado(sessao.getDesgasteAcumulado());
        response.setAlertaFadigaCritica(sessao.getAlertaFadigaCritica());
        return response;
    }
}