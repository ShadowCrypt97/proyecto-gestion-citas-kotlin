package com.doctores.doctores.controllers
import com.doctores.doctores.constants.*
import com.doctores.doctores.domains.request.CreateDoctorRequest
import com.doctores.doctores.domains.request.CreatePatientRequest
import com.doctores.doctores.domains.request.UpdateDoctorRequest
import com.doctores.doctores.domains.request.UpdatePatientRequest
import com.doctores.doctores.domains.responses.CreatePatientResponse
import com.doctores.doctores.domains.responses.HealthCheckResponse
import com.doctores.doctores.services.DoctorService
import com.doctores.doctores.services.PatientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
class PatientController {
    @Autowired
    private lateinit var patientService: PatientService
    @GetMapping(Patient)
    fun getAllPatients(): List<CreatePatientResponse> = patientService.getAllPatients()
    @PostMapping(CreatePatients)
    fun createPatient(
        @RequestBody @Validated request: CreatePatientRequest
    ):CreatePatientResponse = patientService.createPatient(request)
    @GetMapping(GetPatientById)
    fun getPatienttById(
        @PathVariable("id") id: Long
    ): CreatePatientResponse = patientService.getPatientById(id)
    @PutMapping(UpdatePatient)
    fun updatePatient(
        @PathVariable("id") id: Long, @RequestBody @Validated request: UpdatePatientRequest
    ): CreatePatientResponse = patientService.updatePatient(id,request)
}