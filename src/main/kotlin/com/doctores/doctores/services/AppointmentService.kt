package com.doctores.doctores.services

import com.doctores.doctores.domains.entity.Appointment
import com.doctores.doctores.domains.entity.Doctor
import com.doctores.doctores.domains.entity.Patient
import com.doctores.doctores.domains.request.CreateAppointmentRequest
import com.doctores.doctores.domains.request.UpdateAppointmentRequest
import com.doctores.doctores.domains.responses.CreateAppointmentResponse
import com.doctores.doctores.repositories.AppointmentRepository
import com.doctores.doctores.repositories.DoctorRepository
import com.doctores.doctores.repositories.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class AppointmentService {

    @Autowired
    private lateinit var appointmentRepository: AppointmentRepository
    @Autowired
    private lateinit var doctorRepository: DoctorRepository
    @Autowired
    private lateinit var patientRepository: PatientRepository

    fun createAppointment(request: CreateAppointmentRequest): CreateAppointmentResponse {
        var doctor: Doctor? =  doctorRepository.getByDoctorId(request.idDoctor)
        val patient: Patient? = patientRepository.getByDoctorByIdNumber(request.idPaciente)
        if(doctor!=null && patient!=null){
            val appointment = appointmentRepository.save(
                Appointment(
                    horario = request.horario,
                    idDoctor = doctor.idDoctor,
                    especialidad_cita = request.especialidad,
                    identificacionPaciente = request.idPaciente,
                    createdAt = Instant.now(),
                    updatedAt = Instant.now(),
                )
            )
            return CreateAppointmentResponse(
                idPaciente = appointment.identificacionPaciente,
                consultorio = doctor.consultorio,
                especialidad = appointment.especialidad_cita,
                doctor = doctor.nombre,
                horario = appointment.horario
            )
        }else
            throw Error("El doctor o el paciente no existe")

    }

    fun getAllAppointments(): MutableList<CreateAppointmentResponse>? {
        val appointmentList = appointmentRepository.getAllAppointments()
        var response : MutableList<CreateAppointmentResponse>? = mutableListOf()
        val appointments: ListIterator<Appointment>? = appointmentList?.listIterator(1)
        if(appointments!=null){
            while (appointments.hasNext()) {
                val appointment = appointments.next()
                val doctor = doctorRepository.getByDoctorId(appointment.idDoctor)
                if (doctor!=null){
                    if (response != null) {
                        response.add(
                            CreateAppointmentResponse(
                                idPaciente = appointment.identificacionPaciente,
                                especialidad = appointment.especialidad_cita,
                                doctor = doctor.nombre,
                                consultorio = doctor.consultorio,
                                horario = appointment.horario
                            )
                        )
                    }else
                        throw Error("No existen doctores en la lista")
                }else
                    throw Error("No existe el doctor")

            }
        }
        return response
    }

    fun getAppointmentById(id: Long): CreateAppointmentResponse {
        var appointment: Appointment? = appointmentRepository.getAppointmentById(id)
        var doctor: Doctor? =  doctorRepository.getByDoctorId(id)
        if (appointment != null && doctor !==null) {
            return CreateAppointmentResponse(
                idPaciente = appointment.identificacionPaciente,
                especialidad = appointment.especialidad_cita,
                doctor = doctor.nombre,
                consultorio = doctor.consultorio,
                horario = appointment.horario
            )
        } else {
            throw Error("No se encontraron registros para esta cita")
        }
    }

    fun updateAppointment(id: Long, updateRequest: UpdateAppointmentRequest): CreateAppointmentResponse {
        var appointment: Appointment? =  appointmentRepository.getAppointmentById(id)
        if(appointment!=null){
            var doctor: Doctor? =  doctorRepository.getByDoctorId(appointment.idDoctor)
            if(doctor!=null){
                val request = CreateAppointmentRequest(
                    idPaciente = updateRequest.idPaciente?:appointment.identificacionPaciente,
                    especialidad = updateRequest.especialidad?:appointment.especialidad_cita,
                    idDoctor = updateRequest.idDoctor?:doctor.idDoctor,
                    horario = updateRequest.horario?:appointment.horario
                )
                appointmentRepository.updateAppointmentById(id,request.idDoctor,request.idPaciente,request.especialidad,request.horario)
                doctor =  doctorRepository.getByDoctorId(appointment.idDoctor)?:doctor
                return CreateAppointmentResponse(
                    idPaciente = updateRequest.idPaciente?:appointment.identificacionPaciente,
                    especialidad = updateRequest.especialidad?:appointment.especialidad_cita,
                    doctor = doctor.nombre,
                    consultorio = doctor.consultorio,
                    horario = updateRequest.horario?:appointment.horario,
                )
            }else
                throw Error("El doctor con id ${id} no existe")
        }else
            throw Error("La cita con id ${id} no existe")

    }
}