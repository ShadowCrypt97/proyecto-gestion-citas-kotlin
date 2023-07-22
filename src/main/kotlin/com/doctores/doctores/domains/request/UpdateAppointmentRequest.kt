package com.doctores.doctores.domains.request

data class UpdateAppointmentRequest(
    val idPaciente: String?,
    val especialidad: String?,
    val idDoctor: Long?,
    val horario: String?
)