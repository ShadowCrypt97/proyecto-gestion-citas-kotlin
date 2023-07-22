package com.doctores.doctores.repositories

import com.doctores.doctores.domains.entity.Doctor
import com.doctores.doctores.domains.request.CreateDoctorRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.time.Instant


interface DoctorRepository : JpaRepository<Doctor, Long> {
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Query("select * from doctores where id_doctor = :id", nativeQuery = true)
    fun getByDoctorId(id: Long): Doctor?

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Query("select * from doctores", nativeQuery = true)
    fun getAllDoctors(): List<Doctor>?


    @Modifying
    @Transactional
    @Query("UPDATE doctores SET nombre = ?2, apellido = ?3, especialidad = ?4, consultorio = ?5, correo = ?6, updated_at = ?7 WHERE id_doctor = ?1", nativeQuery = true)
    fun updateDoctorById(id:Long, nombre: String, apellido: String, especialidad: String, consultorio: Long, correo: String,updatedAt: Instant): Int
}