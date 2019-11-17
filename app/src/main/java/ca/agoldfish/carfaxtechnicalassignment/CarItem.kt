package ca.agoldfish.carfaxtechnicalassignment


//location will be lat and log
class CarItem(
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
        var _body_type: String){
    constructor() :this("","","","","",0,0,"","",
            "","","","","","","",
            "","")

}

