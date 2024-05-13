package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity

class ContractSecondActivity : ActivityResultContract<People, String?>() {
    override fun createIntent(context: Context, input: People): Intent {
        return Intent(context, SecondActivity::class.java).apply {
            putExtra(KEY3, input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        if (resultCode == AppCompatActivity.RESULT_CANCELED) return "Ты просто вышел назад"
        if (intent == null) return null
        if (resultCode != AppCompatActivity.RESULT_OK) return null

        return intent.extras?.getString(RESULT_KEY)
    }
}