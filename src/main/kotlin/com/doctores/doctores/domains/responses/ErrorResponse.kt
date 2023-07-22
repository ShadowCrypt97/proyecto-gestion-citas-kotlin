package com.doctores.doctores.domains.responses

data class ErrorResponse(
    val id_error: Int,
    val status: Int,
    val message: String,
)
