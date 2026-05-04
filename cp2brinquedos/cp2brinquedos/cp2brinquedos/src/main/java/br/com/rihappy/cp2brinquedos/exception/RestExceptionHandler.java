package br.com.rihappy.cp2brinquedos.exception;

import br.com.rihappy.cp2brinquedos.dto.ErroValidacaoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroValidacaoDTO>> handleArgumentNotValid(MethodArgumentNotValidException ex) {

        List<ErroValidacaoDTO> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new ErroValidacaoDTO(err.getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(erros);
    }
}