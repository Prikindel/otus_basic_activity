package com.example.myapplication.finish

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PeopleFinish(
    val name: String,
    val age: Int
) : Parcelable
