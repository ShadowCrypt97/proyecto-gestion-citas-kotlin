package com.doctores.doctores.repositories

import com.doctores.doctores.domains.entity.Doctor
import com.doctores.doctores.domains.entity.Patient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

interface PatientRepository: JpaRepository<Patient, Long> {
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Query("select * from pacientes where identificacion = :id", nativeQuery = true)
    fun getByDoctorByIdNumber(id: String): Patient?
}