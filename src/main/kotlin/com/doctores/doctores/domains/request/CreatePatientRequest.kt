package com.doctores.doctores.domains.request

import org.jetbrains.annotations.NotNull

data class CreatePatientRequest (
    @field:NotNull("Nombre is required")
    val nombre: String,
    @field:NotNull("Apellido is required")
    val apellido: String,
    @field:NotNull("Identificacion is required")
    val identificacion: String,
    val telefono: Int?
)
