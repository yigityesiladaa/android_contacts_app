package com.yigit.localdatabaselab

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.yigit.localdatabaselab.databinding.ActivityNewPersonBinding

class NewPersonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewPersonBinding


    var person : Person? = null
    var personOperations : PersonDatabaseOperations

    init {
        personOperations = PersonDatabaseOperations(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPersonBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val personId = intent.getIntExtra("personId", -1)

        if(personId == -1){
            person = Person()
            binding.btnDelete.visibility = View.GONE
        }else{
            person = personOperations.getPerson(personId)
            if(person != null){
                binding.nameEditText.setText(person!!.name)
                binding.surnameEditText.setText(person!!.surname)
                binding.emailEditText.setText(person!!.email)
                binding.btnDelete.visibility = View.VISIBLE
            }else{
                binding.btnDelete.visibility = View.GONE
            }
        }
    }

    fun saveContact(view: View) {
        person!!.name = binding.nameEditText.text.toString()
        person!!.surname = binding.surnameEditText.text.toString()
        person!!.email = binding.emailEditText.text.toString()

        if(person!!.id == null){
            personOperations.insertPerson(person!!)
        }else{
            personOperations.updatePerson(person!!)
        }
        setResult(RESULT_OK)
        finish()
    }

    fun deleteContact(view: View) {
        personOperations.deletePerson(person!!.id!!)
        setResult(RESULT_OK)
        finish()
    }
}