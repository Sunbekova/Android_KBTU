package com.example.simpledodoapp.model

import java.util.UUID
import android.os.Parcel
import android.os.Parcelable

data class Pizza(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val shortDescription: String,
    val imageRes: Int,
    val cost: String,
    val isCombo: Boolean = false,
    val attention_mark: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(shortDescription)
        parcel.writeInt(imageRes)
        parcel.writeString(cost)
        parcel.writeByte(if (isCombo) 1 else 0)
        parcel.writeString(attention_mark)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pizza> {
        override fun createFromParcel(parcel: Parcel): Pizza {
            return Pizza(parcel)
        }

        override fun newArray(size: Int): Array<Pizza?> {
            return arrayOfNulls(size)
        }
    }
}


