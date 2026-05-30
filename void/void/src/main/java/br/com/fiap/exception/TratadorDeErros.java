package br.com.fiap.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {

    /**
     * Intercepta erros 400 (Bad Request) gerados pelo @Valid nos DTOs.
     * Em vez de um erro gigante do Spring, devolve uma lista limpa com o campo e o motivo.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex) {
        List<FieldError> erros = ex.getFieldErrors();

        // Converte os erros complexos do Spring para o nosso formato simplificado
        List<DadosErroValidacao> listaDeErros = erros.stream()
                .map(DadosErroValidacao::new)
                .toList();

        return ResponseEntity.badRequest().body(listaDeErros);
    }

    /**
     * Intercepta erros genéricos (opcional, mas excelente para QA)
     * Evita que a API caia e devolva erro 500 vazado para o cliente
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> tratarErro500(Exception ex) {
        return ResponseEntity.internalServerError().body("Erro interno no servidor VOID: " + ex.getLocalizedMessage());
    }

    // ---------------------------------------------------------
    // DTO Interno para formatar a mensagem de erro
    // ---------------------------------------------------------
    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}