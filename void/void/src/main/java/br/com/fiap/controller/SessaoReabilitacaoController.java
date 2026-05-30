package br.com.fiap.controller;

import br.com.fiap.model.SessaoReabilitacao;
import br.com.fiap.service.SessaoReabilitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessoes")
public class SessaoReabilitacaoController {

    @Autowired
    private SessaoReabilitacaoService service;

    @PostMapping
    public ResponseEntity<SessaoReabilitacao> criar(@RequestBody SessaoReabilitacao sessao) {
        return ResponseEntity.ok(service.salvar(sessao));
    }

    @GetMapping
    public ResponseEntity<List<SessaoReabilitacao>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }
    
}