package Entity

class Appointment {
    private var id: String=""
    private var petId: String=""
    private var date: String=""
    private var description: String=""

    constructor()

    constructor(id: String, petId: String, date: String, description: String) {
        this.id = id
        this.petId = petId
        this.date = date
        this.description = description
    }

    var ID: String
        get() = id
        set(value) { id = value }

    var PetID: String
        get() = petId
        set(value) { petId = value }

    var Date: String
        get() = date
        set(value) { date = value }

    var Description: String
        get() = description
        set(value) { description = value }

}