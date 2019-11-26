package ca.agoldfish.carfaxtechnicalassignment.requests.VehicleResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ca.agoldfish.carfaxtechnicalassignment.model.Vehicle;

public class VehicleResponse {

    //this is not being used and will be removed
    @SerializedName("listings")
    @Expose()
    private Vehicle vehicle;

    public Vehicle getVehicle(){
        return vehicle;
    }

    @Override
    public String toString(){
        return "Vehicle = " + vehicle;
    }
}
