package Controller

import Data.IDataManager
import Data.MemoryDataManager
import Entity.Pets
import android.content.Context
import com.poject.proyecto1.R

class PetController {

    private var dataManager: IDataManager = MemoryDataManager
    private lateinit var context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun addPet(pet: Pets) {
        try {
            dataManager.add(pet)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updatePet(pet: Pets) {
        try {
            dataManager.update(pet)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun removePet(pet: Pets) {
        try {
            dataManager.remove(pet.ID)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgRemove))
        }
    }

    fun getPets(): List<Pets> {
        try {
            return dataManager.getAll()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun getById(id: String): Pets {
        try {
            val result = dataManager.getById(id)
            if (result == null) {
                throw Exception(context.getString(R.string.MsgDataNotFound))
            }
            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun getByName(name: String): Pets {
        try {
            val result = dataManager.getByName(name)
            if (result == null) {
                throw Exception(context.getString(R.string.MsgDataNotFound))
            }
            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }
}
