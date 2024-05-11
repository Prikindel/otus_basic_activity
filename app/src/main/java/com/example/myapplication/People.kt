package com.example.myapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class People(
    val name: String,
    val age: Int
) : Parcelable
