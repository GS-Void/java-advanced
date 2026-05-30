package br.com.fiap.controller;

import br.com.fiap.dto.FisioterapeutaRequestDTO;
import br.com.fiap.dto.FisioterapeutaResponseDTO;
import br.com.fiap.model.Fisioterapeuta;
import br.com.fiap.service.FisioterapeutaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fisioterapeutas")
@Tag(name = "Fisioterapeutas", description = "Endpoints para gerenciamento dos profissionais fisioterapeutas")
public class FisioterapeutaController {

    @Autowired
    private FisioterapeutaService service;

    @Operation(
            summary = "Cadastrar um novo fisioterapeuta",
            description = "Cria um novo registro de profissional. Como fazer a requisição: Envie um JSON no corpo contendo nome, cpf, email e o registroProfissional (ex: CREFITO)."
    )
    @PostMapping
    public ResponseEntity<FisioterapeutaResponseDTO> criar(@Valid @RequestBody FisioterapeutaRequestDTO dto) {
        Fisioterapeuta fisioterapeuta = new Fisioterapeuta();
        fisioterapeuta.setNome(dto.getNome());
        fisioterapeuta.setCpf(dto.getCpf());
        fisioterapeuta.setEmail(dto.getEmail());
        fisioterapeuta.setRegistroProfissional(dto.getRegistroProfissional());

        Fisioterapeuta salvo = service.salvar(fisioterapeuta);
        return ResponseEntity.ok(converterParaResponse(salvo));
    }

    @Operation(
            summary = "Listar todos os fisioterapeutas",
            description = "Retorna a lista de todos os fisioterapeutas cadastrados. Como fazer a requisição: Execute um GET sem parâmetros nesta rota."
    )
    @GetMapping
    public ResponseEntity<List<FisioterapeutaResponseDTO>> listarTodos() {
        List<Fisioterapeuta> lista = service.listarTodos();
        List<FisioterapeutaResponseDTO> responses = new ArrayList<>();

        for (Fisioterapeuta f : lista) {
            responses.add(converterParaResponse(f));
        }
        return ResponseEntity.ok(responses);
    }

    @Operation(
            summary = "Buscar fisioterapeuta por ID",
            description = "Localiza um profissional pelo seu ID numérico. Como fazer a requisição: Passe o ID na URL (ex: /api/fisioterapeutas/1)."
    )
    @GetMapping("/{id}")
    public ResponseEntity<FisioterapeutaResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<Fisioterapeuta> fisioterapeuta = service.buscarPorId(id);

        if (fisioterapeuta.isPresent()) {
            return ResponseEntity.ok(converterParaResponse(fisioterapeuta.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Atualizar dados do fisioterapeuta",
            description = "Substitui os dados de um profissional existente. Como fazer a requisição: Passe o ID na URL e envie o JSON completo com os novos dados no corpo da requisição."
    )
    @PutMapping("/{id}")
    public ResponseEntity<FisioterapeutaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody FisioterapeutaRequestDTO dto) {
        Fisioterapeuta fisioterapeuta = new Fisioterapeuta();
        fisioterapeuta.setNome(dto.getNome());
        fisioterapeuta.setCpf(dto.getCpf());
        fisioterapeuta.setEmail(dto.getEmail());
        fisioterapeuta.setRegistroProfissional(dto.getRegistroProfissional());

        Fisioterapeuta atualizado = service.atualizar(id, fisioterapeuta);
        if (atualizado != null) {
            return ResponseEntity.ok(converterParaResponse(atualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Deletar fisioterapeuta",
            description = "Remove um profissional do sistema. Como fazer a requisição: Envie uma requisição DELETE com o ID na URL."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private FisioterapeutaResponseDTO converterParaResponse(Fisioterapeuta fisioterapeuta) {
        FisioterapeutaResponseDTO response = new FisioterapeutaResponseDTO();
        response.setId(fisioterapeuta.getId());
        response.setNome(fisioterapeuta.getNome());
        response.setRegistroProfissional(fisioterapeuta.getRegistroProfissional());
        return response;
    }
}