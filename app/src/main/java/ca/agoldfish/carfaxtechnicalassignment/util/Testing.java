package ca.agoldfish.carfaxtechnicalassignment.util;

import android.util.Log;

import java.util.List;

import ca.agoldfish.carfaxtechnicalassignment.model.Vehicle;

public class Testing {

    //easier way to test the data
    public static void printVehicles(List<Vehicle> list, String tag){
        for(Vehicle vehicle: list)
            Log.d(tag, "printVehicles: ");
    }
}
