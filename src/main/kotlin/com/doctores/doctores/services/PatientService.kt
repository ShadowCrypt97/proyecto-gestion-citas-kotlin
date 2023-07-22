package com.doctores.doctores.services

import com.doctores.doctores.domains.entity.Doctor
import com.doctores.doctores.domains.entity.Patient
import com.doctores.doctores.domains.request.CreateDoctorRequest
import com.doctores.doctores.domains.request.CreatePatientRequest
import com.doctores.doctores.domains.request.UpdatePatientRequest
import com.doctores.doctores.domains.responses.CreateDoctorResponse
import com.doctores.doctores.domains.responses.CreatePatientResponse
import com.doctores.doctores.repositories.DoctorRepository
import com.doctores.doctores.repositories.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class PatientService {
    @Autowired
    private lateinit var patientRepository: PatientRepository
    fun createPatient(request: CreatePatientRequest): CreatePatientResponse {
        val paciente =  patientRepository.save(
            Patient(
                nombre = request.nombre,
                apellido = request.apellido,
                identificacion = request.identificacion,
                telefono = request.telefono,
                createdAt = Instant.now(),
                updatedAt = null
            )
        )
        return CreatePatientResponse(
            id_paciente = paciente.idPatient,
            nombre = paciente.nombre,
            apellido = paciente.apellido,
            identificacion = paciente.identificacion,
            telefono = paciente.telefono,
            createdAt = paciente.createdAt,
            updatedAt = paciente.updatedAt
        )

    }

    fun getAllPatients(): List<CreatePatientResponse>{
        val patientList = patientRepository.findAll()
        val response : MutableList<CreatePatientResponse> = mutableListOf()
        val patients: MutableListIterator<Patient> = patientList.listIterator(1)
        while (patients.hasNext()) {
            val patient = patients.next()
            response.add(
                CreatePatientResponse(
                    id_paciente = patient.idPatient,
                    nombre = patient.nombre,
                    apellido = patient.apellido,
                    identificacion = patient.identificacion,
                    telefono = patient.telefono,
                    createdAt = patient.createdAt,
                    updatedAt = patient.updatedAt
                )
            )
        }
        return response
    }

    fun getPatientById(id: Long): CreatePatientResponse {
        var patient: Optional<Patient> =  patientRepository.findById(id)
        if(patient.isPresent){
            return CreatePatientResponse(
                id_paciente = patient.get().idPatient,
                nombre = patient.get().nombre,
                apellido = patient.get().apellido,
                identificacion = patient.get().identificacion,
                telefono = patient.get().telefono,
                createdAt = patient.get().createdAt,
                updatedAt = patient.get().updatedAt
            )
        }else
            throw Error("El paciente con id ${id} no existe")
    }

    fun updatePatient(id: Long, updatedPatientReq: UpdatePatientRequest): CreatePatientResponse {
        var patient: Optional<Patient> =  patientRepository.findById(id)
        if(patient.isPresent){
            val request = CreatePatientRequest(
                nombre = updatedPatientReq.nombre?:patient.get().nombre,
                apellido = updatedPatientReq.apellido?:patient.get().apellido,
                identificacion = updatedPatientReq.identificacion?:patient.get().identificacion,
                telefono = updatedPatientReq.telefono?:patient.get().telefono
            )
            val updateAt = Instant.now()
            patientRepository.updatePatientById(id,request.nombre,request.apellido,request.identificacion,request.telefono?:0,
                updateAt)
            return CreatePatientResponse(
                id_paciente = patient.get().idPatient,
                nombre = updatedPatientReq.nombre?:patient.get().nombre,
                apellido = updatedPatientReq.apellido?:patient.get().apellido,
                identificacion = updatedPatientReq.identificacion?:patient.get().identificacion,
                telefono = updatedPatientReq.telefono?:patient.get().telefono,
                createdAt = patient.get().createdAt,
                updatedAt = updateAt
            )
        }else
            throw Error("El doctor con id ${id} no existe")
    }
}
