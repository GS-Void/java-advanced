package br.com.fiap.dto;

public record PacienteResponseDTO(
        Long id,
        String nome,
        Double limiteEsforcoCritico
) {}