package com.doctores.doctores.domains.responses

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.time.Instant

data class CreatePatientResponse(
    val id_paciente:  Long,
    val nombre: String,
    val apellido: String,
    val identificacion: String,
    val telefono: String,
    val createdAt: Instant?,
    val updatedAt: Instant?
)