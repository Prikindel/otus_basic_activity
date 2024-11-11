package com.example.myapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class People(
    var city: String = "",
    val id: Int,
    val name: String,
    val age: Int
) : Parcelable
