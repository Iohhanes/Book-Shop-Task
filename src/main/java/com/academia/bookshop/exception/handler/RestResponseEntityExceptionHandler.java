package com.academia.bookshop.exception.handler;

import com.academia.bookshop.exception.ResourceNotFoundException;
import com.academia.bookshop.exception.error.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    ResponseEntity<?> handle(Throwable exception) {
        log.error("Caught unhandled exception: {}", exception.getMessage());
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .reasonPhrase(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(exception.getMessage())
                .timestamp(ZonedDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }

    @ExceptionHandler({ResourceNotFoundException.class, NotFoundException.class})
    ResponseEntity<?> handle(RuntimeException exception) {
        log.error("Caught {}: {}", exception.getClass().getSimpleName(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handle(AccessDeniedException exception) {
        log.error("Caught AccessDeniedException: {}", exception.getMessage());
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .reasonPhrase(HttpStatus.FORBIDDEN.getReasonPhrase())
                .message(exception.getMessage())
                .timestamp(ZonedDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handle(MethodArgumentNotValidException exception) {
        log.error("Caught MethodArgumentNotValidException: {}", exception.getMessage());
        BindingResult result = exception.getBindingResult();
        String message = result.getFieldErrors().stream()
                .map(fieldError -> "Field error in object '"
                        + fieldError.getObjectName()
                        + "'on field '"
                        + fieldError.getField() + "': "
                        + fieldError.getDefaultMessage()
                ).collect(Collectors.joining(";"));
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .reasonPhrase("Field validation error")
                .message(message)
                .timestamp(ZonedDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }
}
