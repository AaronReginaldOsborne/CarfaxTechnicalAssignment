package ca.agoldfish.carfaxtechnicalassignment.model

import android.os.Parcel
import android.os.Parcelable

data class Images(
        var large: Array<String>,
        var medium: Array<String>,
        var small: Array<String>
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.createStringArray(),
            parcel.createStringArray(),
            parcel.createStringArray())

    //default constructor
    constructor() : this(
            emptyArray<String>(),
            emptyArray<String>(),
            emptyArray<String>()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringArray(large)
        parcel.writeStringArray(medium)
        parcel.writeStringArray(small)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Images> {
        override fun createFromParcel(parcel: Parcel): Images {
            return Images(parcel)
        }

        override fun newArray(size: Int): Array<Images?> {
            return arrayOfNulls(size)
        }
    }
}