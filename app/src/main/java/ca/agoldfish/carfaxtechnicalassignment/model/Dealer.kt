package ca.agoldfish.carfaxtechnicalassignment.model

import android.os.Parcel
import android.os.Parcelable

data class Dealer(
        var address: String,
        var carfaxId: String,
        var dealerReviewRating: Int,
        var city: String,
        var state: String,
        var latitude: String,
        var longitude: String,
        var phone: String
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeString(carfaxId)
        parcel.writeInt(dealerReviewRating)
        parcel.writeString(city)
        parcel.writeString(state)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
        parcel.writeString(phone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Dealer> {
        override fun createFromParcel(parcel: Parcel): Dealer {
            return Dealer(parcel)
        }

        override fun newArray(size: Int): Array<Dealer?> {
            return arrayOfNulls(size)
        }
    }

}


