package Data

import Entity.Appointment

object MemoryAppointmentManager : IAppointmentManager {

    private var appointmentList = mutableListOf<Appointment>()

    override fun add(appointment: Appointment) {
        appointmentList.add(appointment)
    }

    override fun remove(id: String) {
        appointmentList.removeIf { it.ID.trim() == id.trim() }
    }

    override fun update(appointment: Appointment) {
        remove(appointment.ID)
        add(appointment)
    }

    override fun getAll(): List<Appointment> = appointmentList

    override fun getById(id: String): Appointment? {
        return try {
            val result = appointmentList.filter { it.ID.trim() == id.trim() }
            if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getByPetId(petId: String): List<Appointment> {
        return try {
            appointmentList.filter { it.PetID.trim() == petId.trim() }
        } catch (e: Exception) {
            throw e
        }
    }
}
