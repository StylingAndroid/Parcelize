package com.stylingandroid.parcelize

import android.os.Parcel
import android.os.Parcelable

inline fun <reified T : Parcelable> T.collapse(): ByteArray {
    val parcel = Parcel.obtain()
    parcel.writeParcelable(this, 0)
    val byteArray = parcel.marshall()
    parcel.recycle()
    return byteArray
}

inline fun <reified T : Parcelable> Class<T>.expand(byteArray: ByteArray): T {
    val parcel = Parcel.obtain()
    parcel.apply {
        unmarshall(byteArray, 0, byteArray.size)
        setDataPosition(0)
    }
    val parcelable =
        parcel.readParcelable<T>(this@expand.classLoader)
            ?: throw InstantiationException("Unable to expand $name")
    parcel.recycle()
    return parcelable
}
