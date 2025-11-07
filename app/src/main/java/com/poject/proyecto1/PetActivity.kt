package com.project.proyecto1

import Controller.PetController
import Entity.Pets
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.util.Calendar

class PetActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var txtId: EditText
    private lateinit var txtName: EditText
    private lateinit var txtSpecies: EditText
    private lateinit var txtBreed: EditText
    private lateinit var txtHistory: EditText
    private lateinit var txtAppointment: EditText
    private lateinit var lbDate: TextView
    private lateinit var petController: PetController
    private var isEditMode: Boolean = false
    private var day: Int = 0
    private var month: Int = 0
    private var year: Int = 0
    private lateinit var menuItemDelete: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet)

        petController = PetController(this)

        txtId = findViewById(R.id.txtId_pet)
        txtName = findViewById(R.id.txtName_pet)
        txtSpecies = findViewById(R.id.txtSpecies_pet)
        txtBreed = findViewById(R.id.txtBreed_pet)
        txtHistory = findViewById(R.id.txtHistory_pet)
        txtAppointment = findViewById(R.id.txtAppointment_pet)
        lbDate = findViewById(R.id.lbDate_pet)

        resetDate()

        findViewById<ImageButton>(R.id.btnSelectDate_pet).setOnClickListener {
            showDatePickerDialog()
        }

        findViewById<ImageButton>(R.id.btnSearchId_pet).setOnClickListener {
            searchPet(txtId.text.trim().toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_crud, menu)
        menuItemDelete = menu!!.findItem(R.id.mnu_delete)
        menuItemDelete.isVisible = isEditMode
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mnu_save -> {
                if (isEditMode) {
                    Util.util.showDialogCondition(this, getString(R.string.TextSaveActionQuestion)) {
                        savePet()
                    }
                } else {
                    savePet()
                }
                true
            }
            R.id.mnu_delete -> {
                Util.util.showDialogCondition(this, getString(R.string.TextDeleteActionQuestion)) {
                    deletePet()
                }
                true
            }
            R.id.mnu_cancel -> {
                cleanScreen()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun resetDate() {
        val calendar = Calendar.getInstance()
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
    }

    private fun showDatePickerDialog() {
        DatePickerDialog(this, this, year, month, day).show()
    }

    private fun getDateFormatString(dayOfMonth: Int, monthValue: Int, yearValue: Int): String {
        return "${if (dayOfMonth < 10) "0" else ""}$dayOfMonth/${if (monthValue < 10) "0" else ""}$monthValue/$yearValue"
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        lbDate.text = getDateFormatString(dayOfMonth, month + 1, year)
    }

    private fun searchPet(id: String) {
        try {
            val pet = petController.getById(id)
            if (pet != null) {
                isEditMode = true
                txtId.setText(pet.ID)
                txtId.isEnabled = false
                txtName.setText(pet.Name)
                txtSpecies.setText(pet.Species)
                txtBreed.setText(pet.Breed)
                txtHistory.setText(pet.History)
                txtAppointment.setText(pet.Appointment)
                menuItemDelete.isVisible = true
            } else {
                Toast.makeText(this, getString(R.string.MsgDataNotFound), Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            cleanScreen()
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun isValidationData(): Boolean {
        return txtId.text.trim().isNotEmpty() &&
                txtName.text.trim().isNotEmpty() &&
                txtSpecies.text.trim().isNotEmpty() &&
                txtBreed.text.trim().isNotEmpty()
    }

    private fun cleanScreen() {
        resetDate()
        isEditMode = false
        txtId.isEnabled = true
        txtId.setText("")
        txtName.setText("")
        txtSpecies.setText("")
        txtBreed.setText("")
        txtHistory.setText("")
        txtAppointment.setText("")
        lbDate.setText("")
        invalidateOptionsMenu()
    }

    private fun savePet() {
        try {
            if (isValidationData()) {
                if (petController.getById(txtId.text.toString().trim()) != null && !isEditMode) {
                    Toast.makeText(this, getString(R.string.MsgDuplicateDate), Toast.LENGTH_LONG).show()
                } else {
                    val pet = Pets()
                    pet.ID = txtId.text.toString()
                    pet.Name = txtName.text.toString()
                    pet.Species = txtSpecies.text.toString()
                    pet.Breed = txtBreed.text.toString()
                    pet.History = txtHistory.text.toString()
                    pet.Appointment = txtAppointment.text.toString()

                    if (!isEditMode)
                        petController.addPet(pet)
                    else
                        petController.updatePet(pet)

                    cleanScreen()
                    Toast.makeText(this, getString(R.string.MsgSaveSuccess), Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Incomplete data", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun deletePet() {
        try {
            petController.removePet(txtId.text.toString())
            cleanScreen()
            Toast.makeText(this, getString(R.string.MsgDeleteSuccess), Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }
}
