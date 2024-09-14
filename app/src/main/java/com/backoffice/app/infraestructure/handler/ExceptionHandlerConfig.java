package com.backoffice.app.infraestructure.handler;

import com.backoffice.core.exception.VoteAssemblyException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Log4j2
public class ExceptionHandlerConfig {

    @ExceptionHandler(value = VoteAssemblyException.class)
    public ResponseEntity<?> voteAssemblyHandler(VoteAssemblyException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        DetailError.builder()
                                .code(HttpStatus.BAD_REQUEST.value())
                                .date(LocalDateTime.now())
                                .message(exception.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> internalErrorHandler(Exception exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        DetailError.builder()
                                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .date(LocalDateTime.now())
                                .message(HttpStatus.INTERNAL_SERVER_ERROR.name())
                                .build()
                );
    }
}
