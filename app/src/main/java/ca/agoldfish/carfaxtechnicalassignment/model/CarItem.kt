package ca.agoldfish.carfaxtechnicalassignment.model

import android.os.Parcel
import android.os.Parcelable


//location will be lat and log
data class CarItem(
        var _id: String,
        var _rating: Float,
        var _imageUrl: String,
        var _year: String,
        var _make: String,
        var _model: String,
        var _trim: String,
        var _price: Int,
        var _milage: Int,
        var _city: String,
        var _state: String,
        var _latitude: String,
        var _longitude: String,
        var _phoneNumber: String,
        var _interior_color: String,
        var _exterior_color: String,
        var _drive_type: String,
        var _transmision: String,
        var _engine: String,
        var _body_type: String,
        var _fuel :String): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readFloat(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
            )

    constructor() :this("",0.0f,"","","","","",0,0,"","",
            "","","","","","","",
            "","","")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeFloat(_rating)
        parcel.writeString(_imageUrl)
        parcel.writeString(_year)
        parcel.writeString(_make)
        parcel.writeString(_model)
        parcel.writeString(_trim)
        parcel.writeInt(_price)
        parcel.writeInt(_milage)
        parcel.writeString(_city)
        parcel.writeString(_state)
        parcel.writeString(_latitude)
        parcel.writeString(_longitude)
        parcel.writeString(_phoneNumber)
        parcel.writeString(_interior_color)
        parcel.writeString(_exterior_color)
        parcel.writeString(_drive_type)
        parcel.writeString(_transmision)
        parcel.writeString(_engine)
        parcel.writeString(_body_type)
        parcel.writeString(_fuel)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CarItem> {
        override fun createFromParcel(parcel: Parcel): CarItem {
            return CarItem(parcel)
        }

        override fun newArray(size: Int): Array<CarItem?> {
            return arrayOfNulls(size)
        }
    }

}

