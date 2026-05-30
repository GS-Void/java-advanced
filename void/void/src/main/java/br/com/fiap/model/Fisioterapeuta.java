package br.com.fiap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TB_VOID_FISIOTERAPEUTA")
public class Fisioterapeuta extends Usuario {

    private String registroProfissional;
}