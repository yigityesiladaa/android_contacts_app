package com.yigit.localdatabaselab

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.yigit.localdatabaselab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var personList = ArrayList<Person>()
    var personDatabaseOperations : PersonDatabaseOperations

    init {
        personDatabaseOperations = PersonDatabaseOperations(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        personList = personDatabaseOperations.getAllPersons()
        val adapter = PersonAdapter(personList,this::rvItemOnClick)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvPersonList.layoutManager = layoutManager
        binding.rvPersonList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        binding.rvPersonList.adapter = adapter
    }

    fun addContact(view: View) {
        val intent = Intent(this, NewPersonActivity::class.java)
        startActivityForResult(intent, 1)
    }

    fun rvItemOnClick(position : Int) {
        val intent = Intent(this, NewPersonActivity::class.java)
        intent.putExtra("personId", personList[position].id)
        startActivityForResult(intent, 2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            personList.clear()
            personList.addAll(personDatabaseOperations.getAllPersons())
            binding.rvPersonList.adapter?.notifyDataSetChanged()
        }
    }
}