package com.example.myapplication

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

const val TAG = "TAG"
const val KEY1 = "key1"
const val KEY2 = "key2"
const val KEY3 = "key3"

private val CODE = 99
val RESULT_KEY = "result_key"

class MainActivity : AppCompatActivity() {

    private var button: Button? = null
    private var imageView: ImageView? = null

    private val takePictures = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { image ->
        imageView?.setImageBitmap(image)
    }

    private val takePictureUri = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        imageView?.setImageURI(uri)
    }

    private val permissionCamera = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        when {
            granted -> {
                // Получили доступ
                takePictures.launch(null)
            }
            !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)-> {
                // Пользователь нажал больше не спрашивать
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    val uri = Uri.fromParts("package", packageName, null)
                    data = uri
                }
                startActivity(intent)
            }
            else -> {
                // Пользователь нажал не разрешать
                Toast.makeText(this, "Потом попробуешь", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val multiPermission = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        val isCamera = it[Manifest.permission.CAMERA]
        val islocation = it[Manifest.permission.LOCATION_HARDWARE]
    }

    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data
        val resultCode = result.resultCode

        if (resultCode == RESULT_OK && data != null) {
            Toast.makeText(this, data.getStringExtra(RESULT_KEY), Toast.LENGTH_SHORT).show()
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Ты просто вышел назад", Toast.LENGTH_SHORT).show()
        }
    }

    // Лаунч через контракт
    private val launcherNewContract = registerForActivityResult(
        ContractSecondActivity()
    ) { info ->
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        imageView = findViewById(R.id.image)

        button?.setOnClickListener {
            val intent = openSecondIntent()
            startActivity(intent)
        }

        // Старый способ вызова
//        button?.setOnClickListener {
//            val intent = openSecondIntent()
//            startActivityForResult(intent, CODE)
//        }

        // новый способ
        button?.setOnClickListener {
//            val intent = openSecondIntent()
//            launcher.launch(intent)

            launcherNewContract.launch(People("NAme", 9))
        }

        findViewById<Button>(R.id.button_pictures).setOnClickListener {
            val isGrantedCamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
            if (!isGrantedCamera) {
                permissionCamera.launch(Manifest.permission.CAMERA)
            }

            multiPermission.launch(arrayOf(Manifest.permission.CAMERA, Manifest.permission.LOCATION_HARDWARE))
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

    }

    fun openSecondIntent() = Intent(this, SecondActivity::class.java).apply {
        putExtra(KEY1, "Hello")
        putExtra(KEY2, 123)
        putExtra(KEY3, People("Maksim", 18))
    }

/*    fun openBrowser() = Intent(
//        Intent.ACTION_VIEW,
//        Uri.parse("https://otus.ru")
//    )
//
//    fun openTel(): Intent {
//        val intent = Intent(
//            Intent.ACTION_DIAL,
//            Uri.parse("qr:89991234567")
//        )
//        return intent
//    }
//
//    fun openMail() = Intent(
//        Intent.ACTION_SENDTO,
//        Uri.parse("mailto:android@otus.ru")
//    )
//
//    fun openMap() = Intent(
//        Intent.ACTION_VIEW,
//        Uri.parse("geo:0,0?q=bar")
//    ).setPackage("com.google.android.apps.maps")*/

    // Старый способ получения результата
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i(TAG, "onActivityResult")

        when (requestCode) {
            CODE -> {
                if (resultCode == RESULT_OK && data != null) {
                    Toast.makeText(this, data.getStringExtra(RESULT_KEY), Toast.LENGTH_SHORT).show()
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Ты просто вышел назад", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "call onRestart main")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "call onStart main")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "call onResume main")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "call onPause main")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "call onStop main")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "call onDestroy main")
    }

}