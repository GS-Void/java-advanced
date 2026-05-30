package br.com.fiap.dto;

import br.com.fiap.model.SessaoReabilitacaoId;
import lombok.Data;

@Data
public class SessaoReabilitacaoResponseDTO {

    private SessaoReabilitacaoId id;
    private Double desgasteAcumulado;
    private Boolean alertaFadigaCritica;

}