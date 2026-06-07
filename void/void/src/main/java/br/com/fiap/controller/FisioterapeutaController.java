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

    @Operation(summary = "Cadastrar um novo fisioterapeuta")
    @PostMapping
    public ResponseEntity<FisioterapeutaResponseDTO> criar(@Valid @RequestBody FisioterapeutaRequestDTO dto) {
        Fisioterapeuta fisioterapeuta = new Fisioterapeuta();
        fisioterapeuta.setNome(dto.nome());
        fisioterapeuta.setCpf(dto.cpf());
        fisioterapeuta.setEmail(dto.email());
        fisioterapeuta.setRegistroProfissional(dto.registroProfissional());

        Fisioterapeuta salvo = service.salvar(fisioterapeuta);
        return ResponseEntity.ok(converterParaResponse(salvo));
    }

    @Operation(summary = "Listar todos os fisioterapeutas")
    @GetMapping
    public ResponseEntity<List<FisioterapeutaResponseDTO>> listarTodos() {
        List<Fisioterapeuta> lista = service.listarTodos();
        List<FisioterapeutaResponseDTO> responses = new ArrayList<>();

        for (Fisioterapeuta f : lista) {
            responses.add(converterParaResponse(f));
        }
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Buscar fisioterapeuta por ID")
    @GetMapping("/{id}")
    public ResponseEntity<FisioterapeutaResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<Fisioterapeuta> fisioterapeuta = service.buscarPorId(id);

        if (fisioterapeuta.isPresent()) {
            return ResponseEntity.ok(converterParaResponse(fisioterapeuta.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualizar dados do fisioterapeuta")
    @PutMapping("/{id}")
    public ResponseEntity<FisioterapeutaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody FisioterapeutaRequestDTO dto) {
        Fisioterapeuta fisioterapeuta = new Fisioterapeuta();
        fisioterapeuta.setNome(dto.nome());
        fisioterapeuta.setCpf(dto.cpf());
        fisioterapeuta.setEmail(dto.email());
        fisioterapeuta.setRegistroProfissional(dto.registroProfissional());

        Fisioterapeuta atualizado = service.atualizar(id, fisioterapeuta);
        if (atualizado != null) {
            return ResponseEntity.ok(converterParaResponse(atualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Deletar fisioterapeuta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private FisioterapeutaResponseDTO converterParaResponse(Fisioterapeuta fisioterapeuta) {

        return new FisioterapeutaResponseDTO(
                fisioterapeuta.getId(),
                fisioterapeuta.getNome(),
                fisioterapeuta.getRegistroProfissional()
        );
    }
}