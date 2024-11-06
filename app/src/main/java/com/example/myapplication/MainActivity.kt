package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)

        button?.setOnClickListener {

        }
    }

    fun openSecondIntent() {

    }

    fun openBrowser() {

    }

    fun openTel() {

    }

    fun openMail() {

    }

    fun openMap() {

    }

}