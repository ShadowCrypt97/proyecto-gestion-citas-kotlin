package com.doctores.doctores.services

import com.doctores.doctores.domains.request.CreatePatientRequest
import com.doctores.doctores.domains.request.UpdatePatientRequest
import com.doctores.doctores.domains.responses.CreatePatientResponse
import org.springframework.stereotype.Service

@Service
class PatientService {
    fun createPatient(request: CreatePatientRequest): CreatePatientResponse {
        return CreatePatientResponse(
            id_paciente = 1,
            nombre = "Sergio",
            apellido = "Gomez",
            identificacion = "1014286295",
            telefono = "3142362540",
            createdAt = null,
            updatedAt = null
        )
    }

    fun getAllPatients(): List<CreatePatientResponse>{
        var response : List<CreatePatientResponse> = listOf(
            CreatePatientResponse(
                id_paciente = 1,
                nombre = "Sergio",
                apellido = "Gomez",
                identificacion = "1014286295",
                telefono = "3142362540",
                createdAt = null,
                updatedAt = null
            )
        )
        return response
    }

    fun getPatientById(id: Long): CreatePatientResponse {
        return CreatePatientResponse(
            id_paciente = 1,
            nombre = "Sergio",
            apellido = "Gomez",
            identificacion = "1014286295",
            telefono = "3142362540",
            createdAt = null,
            updatedAt = null
        )
    }

    fun updatePatient(id: Long, request: UpdatePatientRequest): CreatePatientResponse {
        return CreatePatientResponse(
            id_paciente = 1,
            nombre = "Sergio",
            apellido = "Gomez",
            identificacion = "1014286295",
            telefono = "3142362540",
            createdAt = null,
            updatedAt = null
        )
    }
}
