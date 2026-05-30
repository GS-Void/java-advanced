package br.com.fiap.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ErroAutenticacaoHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    // Dispara quando o usuário tenta acessar sem mandar o token
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        enviarMensagem(response);
    }

    // Dispara quando o usuário manda um token, mas não tem permissão para aquela área
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        enviarMensagem(response);
    }

    private void enviarMensagem(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // Força o erro 403
        response.setContentType("application/json;charset=UTF-8"); // Formata a saída como JSON
        response.getWriter().write("{\"erro\": \"Você não tem a chave\"}");
    }
}