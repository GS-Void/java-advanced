package br.com.fiap.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

public record SessaoReabilitacaoRequestDTO(
        @NotNull(message = "O ID do paciente é obrigatório")
        Long pacienteId,

        @NotNull(message = "A data da sessão é obrigatória")
        LocalDate dataSessao,

        @NotNull(message = "O desgaste não pode ser nulo")
        @PositiveOrZero(message = "O desgaste deve ser zero ou positivo")
        Double desgasteAcumulado
) {}