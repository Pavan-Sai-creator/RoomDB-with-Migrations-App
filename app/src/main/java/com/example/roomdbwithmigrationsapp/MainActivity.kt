package com.example.roomdbwithmigrationsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var database: ContactDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = ContactDatabase.getDatabase(this)

        GlobalScope.launch {
            database.contactDAO().insertContact(Contact(0,"David","2867", Date(),1))
        }

        database.contactDAO().getAllContacts().observe(this, androidx.lifecycle.Observer {
            Log.d("test",it.toString())
        })

    }
}