package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

const val KEY1 = "key1"
const val KEY2 = "key2"
const val KEY3 = "key3"
const val KEY_PEOPLE = "key_people"

class MainActivity : AppCompatActivity() {

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

    fun openSecondIntent(): Intent {
        val people = People(
            id = 100,
            name = "Vasya",
            age = 18
        )

        val intent = Intent(this, SecondActivity::class.java)
            .putExtra(KEY1, 1234)
            .putExtra(KEY2, "119384")
//            .putExtra(KEY_PEOPLE, people)
            .putExtras(
                Bundle().apply {
                    putSerializable(KEY_PEOPLE, people)
                }
            )
        return intent
    }

    fun openBrowser(): Intent {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("otusqr://otus.ru?key=33"))
        return intent
    }

    fun openTel(): Intent {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:89968008080"))
        return intent
    }

    fun openMail(): Intent {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:foo@foo.net"))
        return intent
    }

    fun openMap(): Intent {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=bar"))
            .setPackage("com.google.android.apps.maps")
        return intent
    }

}