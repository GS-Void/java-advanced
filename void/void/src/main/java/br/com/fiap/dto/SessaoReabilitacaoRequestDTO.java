package br.com.fiap.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import java.time.LocalDate;

@Data
public class SessaoReabilitacaoRequestDTO {

    @NotNull(message = "O ID do paciente é obrigatório")
    private Long pacienteId;

    @NotNull(message = "A data da sessão é obrigatória")
    private LocalDate dataSessao;

    @NotNull(message = "O desgaste não pode ser nulo")
    @PositiveOrZero(message = "O desgaste deve ser zero ou positivo")
    private Double desgasteAcumulado;
}