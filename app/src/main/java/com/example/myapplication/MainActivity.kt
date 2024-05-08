package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button

const val TAG = "TAG"

class MainActivity : AppCompatActivity() {

    private var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "call onCreate")
        button = findViewById(R.id.button)
        button?.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
//        finish()
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "call onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "call onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "call onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "call onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "call onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "call onDestroy")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.i(TAG, "call onNewIntent")
    }
}