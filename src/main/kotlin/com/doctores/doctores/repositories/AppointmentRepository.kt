package com.doctores.doctores.repositories

import com.doctores.doctores.domains.entity.Appointment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

interface AppointmentRepository: JpaRepository<Appointment, Long> {
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Query("select * from citas where id_cita = :id", nativeQuery = true)
    fun getAppointmentById(id: Long): Appointment?
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Query("select * from citas", nativeQuery = true)
    fun getAllAppointments(): MutableList<Appointment>?
    @Modifying
    @Transactional
    @Query("UPDATE citas SET id_doctor = ?2, identificacion_paciente = ?3, especialidad = ?4, horario = ?5 WHERE id_cita = ?1", nativeQuery = true)
    fun updateAppointmentById(id:Long, idDoctor: Long, idPaciente: String, especialidad: String, horario: String): Int

}