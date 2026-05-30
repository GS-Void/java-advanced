package br.com.fiap.model;

import br.com.fiap.model.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TB_VOID_PACIENTE")
public class Paciente extends Usuario {

    // Limite máximo que este paciente aguenta antes de disparar o LED de fadiga
    private Double limiteEsforcoCritico;
}