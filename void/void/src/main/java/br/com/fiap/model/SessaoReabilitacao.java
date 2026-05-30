package br.com.fiap.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_VOID_SESSAO_REABILITACAO")
public class SessaoReabilitacao {

    @EmbeddedId
    private SessaoReabilitacaoId id;

    // Percentual de desgaste captado pelo Arduino
    private Double desgasteAcumulado;

    // Flag que será validada no PL/SQL com IF/ELSE para travar o treino
    private Boolean alertaFadigaCritica;

    @ManyToOne
    @MapsId("pacienteId")
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}