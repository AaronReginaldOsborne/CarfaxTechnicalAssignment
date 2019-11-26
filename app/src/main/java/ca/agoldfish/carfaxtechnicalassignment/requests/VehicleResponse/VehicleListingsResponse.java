package ca.agoldfish.carfaxtechnicalassignment.requests.VehicleResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ca.agoldfish.carfaxtechnicalassignment.model.Vehicle;
import ca.agoldfish.carfaxtechnicalassignment.model.VehicleTest;
import ca.agoldfish.carfaxtechnicalassignment.model.VehicleTest2;

public class VehicleListingsResponse {

    @SerializedName("listings")
    @Expose()
    private List<VehicleTest2> vehicles;

    public List<VehicleTest2> getVehicles(){
        return vehicles;
    }

    @Override
    public String toString(){
        return "Vehicles = " + vehicles;
    }
}
