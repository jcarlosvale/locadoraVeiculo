package com.study.locadora.controller.exception;

import com.study.locadora.dto.ErrorDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolationException(final ConstraintViolationException exception) {
        var messages = exception
                .getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));
        log.error(exception.getConstraintViolations());
        log.error("Validation errors: {}", messages);
        var errorDto =
                ErrorDto.builder()
                        .codigo(HttpStatus.BAD_REQUEST.value())
                        .mensagem(messages)
                        .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

//    @ExceptionHandler(value = {
//            CESTExistsException.class,
//            ProdutoEANExistsException.class
//        })
//    public ResponseEntity<ErrorDto> handleEntityExistsException(final RuntimeException exception) {
//        log.error("Entity exists: {}", exception.getMessage());
//        var errorDto =
//                ErrorDto.builder()
//                        .codigo(HttpStatus.CONFLICT.value())
//                        .mensagem(exception.getMessage())
//                        .build();
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto);
//    }

//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
//        var messages = exception
//                .getAllErrors()
//                .stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .collect(Collectors.joining("; "));
//        log.error(exception.getAllErrors());
//        log.error("Validation errors: {}", messages);
//        var errorDto =
//                ErrorDto.builder()
//                        .codigo(HttpStatus.BAD_REQUEST.value())
//                        .mensagem(messages)
//                        .build();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
//    }

//    @ExceptionHandler(value = EntityNotFoundException.class)
//    public ResponseEntity<ErrorDto> handleEntityNotFoundException(final EntityNotFoundException exception) {
//        log.error("Entity does not exist: {}", exception.getMessage());
//        var errorDto =
//                ErrorDto.builder()
//                        .codigo(HttpStatus.BAD_REQUEST.value())
//                        .mensagem(exception.getMessage())
//                        .build();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
//    }

//    @ExceptionHandler(value = DataIntegrityViolationException.class)
//    public ResponseEntity<ErrorDto> handleDataIntegrityViolationException(final DataIntegrityViolationException exception) {
//        log.error("Data Integrity Violation: {}", exception.getMessage());
//        var errorDto =
//                ErrorDto.builder()
//                        .codigo(HttpStatus.CONFLICT.value())
//                        .mensagem("Violacao de constraint")
//                        .build();
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto);
//    }

}