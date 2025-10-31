package Data

import Entity.Pets

interface IDataManager {
    fun add(pet: Pets)
    fun update(pet: Pets)
    fun remove(id: String)
    fun getAll(): List<Pets>
    fun getById(id: String): Pets?
    fun getByName(name: String): Pets?
}
