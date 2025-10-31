package Data

import Entity.Appointment

interface IAppointmentManager {
    fun add(appointment: Appointment)
    fun update(appointment: Appointment)
    fun remove(id: String)
    fun getAll(): List<Appointment>
    fun getById(id: String): Appointment?
    fun getByPetId(petId: String): List<Appointment>
}
