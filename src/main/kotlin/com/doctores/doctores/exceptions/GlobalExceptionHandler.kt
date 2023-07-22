package com.doctores.doctores.exceptions

import com.doctores.doctores.domains.responses.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorResponse> {
        // Personalizar la respuesta de error según la excepción recibida
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(
            id_error = Instant.now().nano,
            status = HttpStatus.BAD_REQUEST.value(),
            message = ex.message?:"Error no controlado"
        ))
    }
}