package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "TAG"

class SecondActivity : AppCompatActivity() {

    private var button: Button? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.i(TAG, "call onCreate second")

//        Log.i(TAG, "otusqr key = ${intent.data?.getQueryParameter("key")}")

//        val id = intent.extras?.get(KEY1) as Int
//        val title = intent.extras?.getInt(KEY2, 10)
//        val subtitle = intent.extras?.getString(KEY3, "not found")
//        Log.i(TAG, "id $id and title $title subtitle $subtitle")

        val people = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.extras?.getParcelable(KEY_PEOPLE, People::class.java)
        } else {
            intent.extras?.getParcelable<People>(KEY_PEOPLE)
        }

//        val people: People? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            intent.getSerializableExtra(KEY_PEOPLE, People::class.java)
//        } else {
//            intent.getSerializableExtra(KEY_PEOPLE) as People
//        }

        fake(people)
        findViewById<TextView>(R.id.text).text = "${people?.name} ${people?.age} city ${people?.city}"

        button = findViewById(R.id.button2)
        button?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.back)?.setOnClickListener {
            val result = findViewById<EditText>(R.id.result_info).text.toString()

            val intent = Intent().putExtra(RESULT_KEY, result)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(RESULT_CANCELED)
    }

    fun fake(people: People?) {
        people?.city = "HA-ha-ha-ha"
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