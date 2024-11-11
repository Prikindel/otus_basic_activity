package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

const val KEY1 = "key1"
const val KEY2 = "key2"
const val KEY3 = "key3"

private const val SECOND_ACTIVITY_CODE = 123

class MainActivity : AppCompatActivity() {

    private var button: Button? = null
    private var imageView: ImageView? = null

    private val permissionCamera = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        when {
            granted -> {
                // получили доступ
                takePictures.launch(null)
            }
            !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", packageName, null)
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
        val isLocation = it[Manifest.permission.ACCESS_FINE_LOCATION]

        Log.i("TAG", "isCamera $isCamera; isLocation $isLocation")
    }

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

    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data
        val resultCode = result.resultCode

        if (resultCode == RESULT_OK && data != null) {
            val result = data.getStringExtra(RESULT_KEY) ?: "Это не то, что мы ожидали"
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Ты просто вышел", Toast.LENGTH_SHORT).show()
        }
    }

    private val launcherNewContract = registerForActivityResult(
        ContractSecondActivity()
    ) { result ->
        Toast.makeText(this, "new $result", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        imageView = findViewById(R.id.image)

        button?.setOnClickListener {
            val intent = openSecondIntent()
//            startActivityForResult(intent, SECOND_ACTIVITY_CODE)
            val people = People(
                id = 100,
                name = "Vasya",
                age = 18
            )
            launcher.launch(intent)
//            launcherNewContract.launch(people)
        }

        findViewById<Button>(R.id.button_pictures).setOnClickListener {
//            takePictureUri.launch("image/*")
//            takePictures.launch(null)
            val isGrantedCamera = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED

//            if (!isGrantedCamera) {
//                permissionCamera.launch(Manifest.permission.CAMERA)
//            } else {
//                takePictureUri.launch("image/*")
//            }

//            permissionCamera.launch(Manifest.permission.CAMERA)

            multiPermission.launch(
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            )
        }
    }

    fun openSecondIntent(): Intent {
        val people = People(
            id = 100,
            name = "Vasya",
            age = 18
        )

        val intent = Intent(this, SecondActivity::class.java)
//            .putExtra(KEY1, 1234)
//            .putExtra(KEY2, "119384")
            .putExtra(KEY_PEOPLE, people)
//            .putExtras(
//                Bundle().apply {
//                    putSerializable(KEY_PEOPLE, people)
//                }
//            )
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("TAG", "onActivityResult")

        when(requestCode) {
            SECOND_ACTIVITY_CODE -> {
                if (resultCode == RESULT_OK && data != null) {
                    val result = data.getStringExtra(RESULT_KEY) ?: "Это не то, что мы ожидали"
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Ты просто вышел", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}