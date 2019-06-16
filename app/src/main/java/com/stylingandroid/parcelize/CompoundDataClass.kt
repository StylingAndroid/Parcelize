package com.stylingandroid.parcelize

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CompoundDataClass @JvmOverloads constructor(
    val name: String,
    val simpleDataClass: SimpleDataClass,
    @Transient val transientString: String = ""
) : Parcelable {

    companion object : Parceler<CompoundDataClass> {

        override fun create(parcel: Parcel) =
            CompoundDataClass(
                parcel.readString()!!,
                parcel.readParcelable(SimpleDataClass::class.java.classLoader)!!
            )

        override fun CompoundDataClass.write(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeParcelable(simpleDataClass, flags)
        }
    }
}
