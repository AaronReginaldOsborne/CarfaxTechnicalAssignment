package ca.agoldfish.carfaxtechnicalassignment.util;

import android.util.Log;

import java.util.List;

import ca.agoldfish.carfaxtechnicalassignment.model.VehicleTest2;

public class Testing {

    //easier way to test the data
    public static void printVehicles(List<VehicleTest2> list, String tag){
        for(VehicleTest2 vehicle: list)
            Log.d(tag, "printVehicles: ");
    }
}
