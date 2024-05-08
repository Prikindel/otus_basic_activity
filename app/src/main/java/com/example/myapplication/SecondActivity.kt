package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.i(TAG, "call onCreate second")
        button = findViewById(R.id.button2)
        button?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
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