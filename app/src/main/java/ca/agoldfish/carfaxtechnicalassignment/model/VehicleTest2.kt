package ca.agoldfish.carfaxtechnicalassignment.model

import android.os.Parcel
import android.os.Parcelable

data class VehicleTest2 (
    var badge: String,
    var bodytype: String,
    var currentPrice: Int,
    var dealer: Dealer,
    var drivetype: String,
    var engine: String,
    var exteriorColor: String,
    var fuel: String,
    var id: String,
    var imageCount : Int,
    var images: Images,
    var interiorColor: String,
    var make: String,
    var model: String,
    var mileage: Int,
    var transmission: String,
    var trim: String,
    var year: String
    ) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readParcelable(Dealer::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readParcelable(Images::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(badge)
        parcel.writeString(bodytype)
        parcel.writeInt(currentPrice)
        parcel.writeParcelable(dealer, flags)
        parcel.writeString(drivetype)
        parcel.writeString(engine)
        parcel.writeString(exteriorColor)
        parcel.writeString(fuel)
        parcel.writeString(id)
        parcel.writeInt(imageCount)
        parcel.writeParcelable(images, flags)
        parcel.writeString(interiorColor)
        parcel.writeString(make)
        parcel.writeString(model)
        parcel.writeInt(mileage)
        parcel.writeString(transmission)
        parcel.writeString(trim)
        parcel.writeString(year)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VehicleTest2> {
        override fun createFromParcel(parcel: Parcel): VehicleTest2 {
            return VehicleTest2(parcel)
        }

        override fun newArray(size: Int): Array<VehicleTest2?> {
            return arrayOfNulls(size)
        }
    }
}