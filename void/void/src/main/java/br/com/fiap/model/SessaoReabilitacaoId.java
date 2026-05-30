package br.com.fiap.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Embeddable
public class SessaoReabilitacaoId implements Serializable {

    private Long pacienteId;
    private LocalDate dataSessao;
}