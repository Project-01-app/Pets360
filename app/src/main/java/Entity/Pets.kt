package Entity

import android.graphics.Bitmap

class Pets {

    private var id: String=""
    private var name: String=""
    private var species: String=""
    private var breed: String=""
    private var history: String=""
    private var appointment: String=""
    private  lateinit var Photo: Bitmap

    constructor()

    constructor(id: String, name: String, species: String,
        breed: String, history: String, appointment: String, photo: Bitmap
    ){
        this.id= id
        this.name= name
        this.species= species
        this.breed= breed
        this.history= history
        this.appointment= appointment
        this.Photo= photo
    }

    var ID: String
        get() = this.id
        set(value)  {this.id=value}
    var Name: String
        get() = this.name
        set(value)  {this.name=value}
    var Species: String
        get() = this.species
        set(value)  {this.species=value}
    var Breed: String
        get() = this.breed
        set(value)  {this.breed=value}
    var History: String
        get() = this.history
        set(value)  {this.history=value}
    var Appointment: String
        get() = this.appointment
        set(value)  {this.appointment=value}

}