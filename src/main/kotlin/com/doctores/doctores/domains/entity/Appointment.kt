package com.doctores.doctores.domains.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "citas")
open class Appointment (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cita")
    open var idAppointment: Long=0,

    @Column(name="horario")
    open var horario: String,

    @Column(name="id_doctor")
    open var idDoctor: Long=0,

    @Column(name="especialidad")
    open var especialidad_cita: String,

    @Column(name="identificacion_paciente")
    open var identificacionPaciente: String,

    @Column(name="created_at")
    open var createdAt: Instant?,

    @Column(name="updated_at")
    open var updatedAt: Instant?
)