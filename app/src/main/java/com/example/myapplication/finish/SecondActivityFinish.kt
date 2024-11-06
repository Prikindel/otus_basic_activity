package com.example.myapplication.finish

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class SecondActivityFinish : AppCompatActivity() {

    private var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.i(TAG, "call onCreate second")

        val key1 = intent.extras?.getString(KEY1, "Default") ?: "Def2"
        val key2 = intent.extras?.getString(KEY2, "def3")
        val people = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.extras?.getParcelable(KEY3, PeopleFinish::class.java)
        } else {
            intent.extras?.getParcelable<PeopleFinish>(KEY3)
        }

        findViewById<TextView>(R.id.text).setText(
            "${people?.name} - ${people?.age}"
        )

        button = findViewById(R.id.button2)
        button?.setOnClickListener {
            val intent = Intent(this, MainActivityFinish::class.java)
            startActivity(intent)
        }
//        finish()
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "call onRestart second")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "call onStart second")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "call onResume second")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "call onPause second")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "call onStop second")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "call onDestroy second")
    }
}