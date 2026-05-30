package br.com.fiap.controller;

import br.com.fiap.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação", description = "Endpoints para geração de chaves de acesso JWT")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Operation(
            summary = "Gerar chave de acesso (Token JWT)",
            description = "Gera um token válido por 2 horas. Como usar: Envie qualquer e-mail na URL para simular o login e receber a sua chave."
    )
    @PostMapping("/login/{email}")
    public ResponseEntity<String> gerarToken(@PathVariable String email) {

        String token = tokenService.gerarToken(email);
        return ResponseEntity.ok(token);
    }
}