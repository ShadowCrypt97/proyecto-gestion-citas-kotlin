package com.doctores.doctores.services

import com.doctores.doctores.domains.entity.Doctor
import com.doctores.doctores.domains.request.CreateDoctorRequest
import com.doctores.doctores.domains.request.UpdateDoctorRequest
import com.doctores.doctores.domains.responses.CreateDoctorResponse
import com.doctores.doctores.repositories.DoctorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class DoctorService {
    @Autowired
    private lateinit var doctorRepository: DoctorRepository
    fun createDoctor(request: CreateDoctorRequest): CreateDoctorResponse{
        val doctor =  doctorRepository.save(
            Doctor(
                nombre = request.nombre,
                apellido = request.apellido,
                especialidad = request.especialidad,
                correo = request.correo,
                consultorio = request.consultorio,
                createdAt = Instant.now(),
                updatedAt = null
            )
        )
        return CreateDoctorResponse(
            idDoctor = doctor.idDoctor,
            nombre = request.nombre,
            apellido = request.apellido,
            especialidad = request.especialidad,
            correo = request.correo,
            consultorio = request.consultorio,
            createAt = Instant.now(),
            updateAt = doctor.updatedAt
        )
    }

    fun getAllDoctors(): MutableList<CreateDoctorResponse>? {
        val doctorsList = doctorRepository.getAllDoctors()
        var response : MutableList<CreateDoctorResponse>? = mutableListOf()
        val doctors: ListIterator<Doctor>? = doctorsList?.listIterator(1)
        if(doctors!=null){
            while (doctors.hasNext()) {
                val doctor = doctors.next()
                if (response != null) {
                    response.add(CreateDoctorResponse(
                        idDoctor = doctor.idDoctor,
                        nombre = doctor.nombre,
                        apellido = doctor.apellido,
                        especialidad = doctor.especialidad,
                        correo = doctor.correo,
                        consultorio = doctor.consultorio,
                        createAt = doctor.createdAt,
                        updateAt = doctor.updatedAt
                    ))
                }else
                    throw Error("No existen doctores en la lista")
            }
        }
        return response
    }

    fun getDoctorById(id: Long): CreateDoctorResponse {
        var doctor: Doctor? =  doctorRepository.getByDoctorId(id)
        if (doctor!=null){
            return CreateDoctorResponse(
                idDoctor = doctor.idDoctor,
                nombre = doctor.nombre,
                apellido = doctor.apellido,
                especialidad = doctor.especialidad,
                correo = doctor.correo,
                consultorio = doctor.consultorio,
                createAt = doctor.createdAt,
                updateAt = doctor.updatedAt
            )
        }else{
            throw Error("El usuario no fué encontrado exitosamente")
        }
    }

    fun updateDoctor(id: Long, updateRequest: UpdateDoctorRequest): CreateDoctorResponse{
        var doctor: Doctor? =  doctorRepository.getByDoctorId(id)
        if(doctor!=null){
            val request = CreateDoctorRequest(
                nombre = updateRequest.nombre?:doctor.nombre,
                apellido = updateRequest.apellido?:doctor.apellido,
                especialidad = updateRequest.especialidad?:doctor.especialidad,
                correo = updateRequest.correo?:doctor.correo,
                consultorio = updateRequest.consultorio?:doctor.consultorio,
            )
            val updateAt = Instant.now()
            doctorRepository.updateDoctorById(id,request.nombre,request.apellido,request.especialidad,request.consultorio,request.correo?:"null",
                updateAt)
            return CreateDoctorResponse(
                idDoctor = doctor.idDoctor,
                nombre = updateRequest.nombre?:doctor.nombre,
                apellido = updateRequest.apellido?:doctor.apellido,
                especialidad = updateRequest.especialidad?:doctor.especialidad,
                correo = updateRequest.correo?:doctor.correo,
                consultorio = updateRequest.consultorio?:doctor.consultorio,
                updateAt = updateAt,
                createAt = doctor.createdAt
            )
        }else
            throw Error("El doctor con id ${id} no existe")
    }
}