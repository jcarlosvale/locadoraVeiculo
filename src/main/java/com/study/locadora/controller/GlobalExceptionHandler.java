package com.study.locadora.controller;

import com.study.locadora.dto.ErrorDto;
import com.study.locadora.exception.CarroJaExisteException;
import com.study.locadora.exception.CarroNaoExisteException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorDto> handleEmptyResultDataAccessException(final EmptyResultDataAccessException exception) {
        log.error("Exception: " + exception);
        log.error("Exception message: " + exception.getMessage());

        var dto = ErrorDto.builder()
                .codigo(HttpStatus.BAD_REQUEST.value())
                .mensagem("Registro não existente: " + exception.getMessage())
                .build();


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolationException(final MethodArgumentNotValidException exception) {

        var messages = exception
                .getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.joining("; "));



        log.error(exception.getAllErrors());
        log.error("Validation errors: {}", messages);


        var errorDto =
                ErrorDto.builder()
                        .codigo(HttpStatus.BAD_REQUEST.value())
                        .mensagem(messages)
                        .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(CarroJaExisteException.class)
    public ResponseEntity<ErrorDto> handleCarroJaExisteException(final CarroJaExisteException exception) {
        log.error("Exception: " + exception);
        log.error("Exception message: " + exception.getMessage());

        final var dto = ErrorDto.builder()
                .codigo(HttpStatus.BAD_REQUEST.value())
                .mensagem(exception.getMessage())
                .build();


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @ExceptionHandler(CarroNaoExisteException.class)
    public ResponseEntity<ErrorDto> handleCarroNaoExisteException(final CarroNaoExisteException exception) {
        log.error("Exception: " + exception);
        log.error("Exception message: " + exception.getMessage());

        final var dto = ErrorDto.builder()
                .codigo(HttpStatus.BAD_REQUEST.value())
                .mensagem(exception.getMessage())
                .build();


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

}
