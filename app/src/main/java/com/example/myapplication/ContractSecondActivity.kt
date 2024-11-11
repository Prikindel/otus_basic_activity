package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity

const val KEY_PEOPLE = "key_people"
const val RESULT_KEY = "result_key"

class ContractSecondActivity : ActivityResultContract<People, String?>() {
    override fun createIntent(context: Context, input: People): Intent {
        val intent = Intent(context, SecondActivity::class.java)
        intent.putExtra(KEY_PEOPLE, input)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        when {
            resultCode == AppCompatActivity.RESULT_CANCELED -> return "Ты просто вышел"
            intent == null || resultCode != AppCompatActivity.RESULT_OK -> return null
        }

        return intent?.extras?.getString(RESULT_KEY)
    }
}