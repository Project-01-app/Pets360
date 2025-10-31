package Controller

import Data.IAppointmentManager
import Data.MemoryAppointmentManager
import Entity.Appointment
import android.content.Context
import com.poject.proyecto1.R

class AppointmentController {

    private var appointmentManager: IAppointmentManager = MemoryAppointmentManager
    private lateinit var context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun addAppointment(appointment: Appointment) {
        try {
            appointmentManager.add(appointment)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateAppointment(appointment: Appointment) {
        try {
            appointmentManager.update(appointment)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun removeAppointment(id: String) {
        try {
            appointmentManager.remove(id)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgRemove))
        }
    }

    fun getAppointments(): List<Appointment> {
        try {
            return appointmentManager.getAll()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun getById(id: String): Appointment {
        try {
            val result = appointmentManager.getById(id)
            if (result == null) {
                throw Exception(context.getString(R.string.MsgDataNotFound))
            }
            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun getByPetId(petId: String): List<Appointment> {
        try {
            return appointmentManager.getByPetId(petId)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }
}
