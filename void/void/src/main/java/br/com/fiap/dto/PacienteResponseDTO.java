package br.com.fiap.dto;

import lombok.Data;

@Data
public class PacienteResponseDTO {

    private Long id;
    private String nome;
    private Double limiteEsforcoCritico;

}