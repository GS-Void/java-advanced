package br.com.fiap.controller;

import br.com.fiap.dto.FisioterapeutaRequestDTO;
import br.com.fiap.dto.FisioterapeutaResponseDTO;
import br.com.fiap.model.Fisioterapeuta;
import br.com.fiap.service.FisioterapeutaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fisioterapeutas")
public class FisioterapeutaController {

    @Autowired
    private FisioterapeutaService service;

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

    @GetMapping
    public ResponseEntity<List<FisioterapeutaResponseDTO>> listarTodos() {
        List<Fisioterapeuta> lista = service.listarTodos();
        List<FisioterapeutaResponseDTO> responses = new ArrayList<>();

        for (Fisioterapeuta f : lista) {
            responses.add(converterParaResponse(f));
        }
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FisioterapeutaResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<Fisioterapeuta> fisioterapeuta = service.buscarPorId(id);

        if (fisioterapeuta.isPresent()) {
            return ResponseEntity.ok(converterParaResponse(fisioterapeuta.get()));
        }
        return ResponseEntity.notFound().build();
    }

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