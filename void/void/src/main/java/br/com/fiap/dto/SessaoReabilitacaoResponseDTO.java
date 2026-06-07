package br.com.fiap.dto;

import br.com.fiap.model.SessaoReabilitacaoId;

public record SessaoReabilitacaoResponseDTO(
        SessaoReabilitacaoId id,
        Double desgasteAcumulado,
        Boolean alertaFadigaCritica
) {}