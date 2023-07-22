package com.doctores.doctores.repositories

import com.doctores.doctores.domains.entity.Doctor
import com.doctores.doctores.domains.entity.Patient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

interface PatientRepository: JpaRepository<Patient, Long> {
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Query("select * from pacientes where identificacion = :id", nativeQuery = true)
    fun getByDoctorByIdNumber(id: String): Patient?
    @Modifying
    @Transactional
    @Query("UPDATE pacientes SET nombre = ?2, apellido = ?3, identificacion = ?4, telefono = ?5, updated_at = ?6 WHERE id_paciente = ?1", nativeQuery = true)
    fun updatePatientById(id:Long, nombre: String, apellido: String, identificacion: String, telefono: Int, updatedAt: Instant): Int

}