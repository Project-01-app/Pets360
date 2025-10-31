package Data

import Entity.Pets

object MemoryDataManager : IDataManager {

    private var petList = mutableListOf<Pets>()

    override fun add(pet: Pets) {
        petList.add(pet)
    }

    override fun remove(id: String) {
        petList.removeIf { it.ID.trim() == id.trim() }
    }

    override fun update(pet: Pets) {
        remove(pet.ID)
        add(pet)
    }

    override fun getAll(): List<Pets> = petList

    override fun getById(id: String): Pets? {
        return try {
            val result = petList.filter { it.ID.trim() == id.trim() }
            if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getByName(name: String): Pets? {
        return try {
            val result = petList.filter { it.Name.trim() == name.trim() }
            if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }
}
