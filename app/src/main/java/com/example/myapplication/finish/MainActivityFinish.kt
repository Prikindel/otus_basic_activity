package com.example.myapplication.finish

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

const val TAG = "TAG"
const val KEY1 = "key1"
const val KEY2 = "key2"
const val KEY3 = "key3"

class MainActivityFinish : AppCompatActivity() {

    private var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)

        button?.setOnClickListener {
            val intent = openSecondIntent()
            startActivity(intent)
        }
    }

    fun openSecondIntent() = Intent(this, SecondActivityFinish::class.java).apply {
        putExtra(KEY1, "Hello")
        putExtra(KEY2, 123)
        putExtra(KEY3, PeopleFinish("Maksim", 18))
    }

    fun openBrowser() = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("https://otus.ru")
    )

    fun openTel(): Intent {
        val intent = Intent(
            Intent.ACTION_DIAL,
            Uri.parse("qr:89991234567")
        )
        return intent
    }

    fun openMail() = Intent(
        Intent.ACTION_SENDTO,
        Uri.parse("mailto:android@otus.ru")
    )

    fun openMap() = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("geo:0,0?q=bar")
    ).setPackage("com.google.android.apps.maps")

}