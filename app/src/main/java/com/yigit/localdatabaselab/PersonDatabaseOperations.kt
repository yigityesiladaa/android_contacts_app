package com.yigit.localdatabaselab

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class PersonDatabaseOperations(context: Context) {
    var dbOpenHelper : DatabaseOpenHelper
    var dbPerson : SQLiteDatabase? = null

    init {
        dbOpenHelper = DatabaseOpenHelper(context,"personDb",null,1)
    }

    fun openDb(){
        dbPerson = dbOpenHelper.writableDatabase
    }

    fun closeDb(){
        if(dbPerson != null && dbPerson!!.isOpen){
            dbPerson!!.close()
        }
    }

    fun insertPerson(person : Person){
        val cv = ContentValues()
        cv.put("name",person.name)
        cv.put("surname",person.surname)
        cv.put("email",person.email)

        openDb()
        dbPerson!!.insert("person",null,cv)
        closeDb()
    }

    fun updatePerson(person : Person){
        val cv = ContentValues()
        cv.put("name",person.name)
        cv.put("surname",person.surname)
        cv.put("email",person.email)

        openDb()
        dbPerson!!.update("person",cv,"id = ?", arrayOf(person.id.toString()))
        closeDb()
    }

    fun deletePerson(id : Int){
        openDb()
        dbPerson!!.delete("person","id = ?", arrayOf(id.toString()))
        closeDb()
    }

    fun getAllPersons() : ArrayList<Person>{
        val personList = ArrayList<Person>()
        openDb()
        val sql = "SELECT * FROM person order by name DESC"
        val cursor = dbPerson!!.rawQuery(sql,null)
        if(cursor.moveToFirst()){
            var person : Person
            do {
                person = Person()
                //Database Column Indexes
                val idColumnIndex  = cursor.getColumnIndex("id")
                val nameColumnIndex  = cursor.getColumnIndex("name")
                val surnameColumnIndex  = cursor.getColumnIndex("surname")
                val emailColumnIndex  = cursor.getColumnIndex("email")
                //Database Column Values
                person.id = cursor.getInt(idColumnIndex)
                person.name = cursor.getString(nameColumnIndex)
                person.surname = cursor.getString(surnameColumnIndex)
                person.email = cursor.getString(emailColumnIndex)

                personList.add(person)

            }while (cursor.moveToNext())
        }
        closeDb()

        return personList
    }

    fun getPerson(id : Int) : Person?{
        var person : Person? = null
        openDb()
        val sql = "SELECT * FROM person WHERE id = ?"
        val cursor = dbPerson!!.rawQuery(sql, arrayOf(id.toString()))
        if(cursor.moveToFirst()){
            //Database Column Indexes
            val idColumnIndex  = cursor.getColumnIndex("id")
            val nameColumnIndex  = cursor.getColumnIndex("name")
            val surnameColumnIndex  = cursor.getColumnIndex("surname")
            val emailColumnIndex  = cursor.getColumnIndex("email")
            //Database Column Values
            person = Person()
            person.id = cursor.getInt(idColumnIndex)
            person.name = cursor.getString(nameColumnIndex)
            person.surname = cursor.getString(surnameColumnIndex)
            person.email = cursor.getString(emailColumnIndex)
        }
        closeDb()

        return person
    }
}