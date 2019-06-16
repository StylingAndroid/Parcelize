package com.stylingandroid.parcelize

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SimpleDataClass(
    val name: String,
    val age: Int
) : Parcelable
