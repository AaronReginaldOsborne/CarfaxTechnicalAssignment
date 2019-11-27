package ca.agoldfish.carfaxtechnicalassignment.requests.VehicleResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ca.agoldfish.carfaxtechnicalassignment.model.Vehicle;

public class VehicleListingsResponse {

    @SerializedName("listings")
    @Expose()
    private List<Vehicle> vehicles;

    public List<Vehicle> getVehicles(){
        return vehicles;
    }

    @Override
    public String toString(){
        return "Vehicles = " + vehicles;
    }
}
