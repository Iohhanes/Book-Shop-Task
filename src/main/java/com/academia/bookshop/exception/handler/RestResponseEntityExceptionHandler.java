package com.academia.bookshop.exception.handler;

import com.academia.bookshop.exception.ResourceNotFoundException;
import com.academia.bookshop.exception.error.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;

import java.time.ZonedDateTime;

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

    // TODO process another exceptions
}
