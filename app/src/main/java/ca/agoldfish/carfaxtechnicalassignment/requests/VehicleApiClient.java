package ca.agoldfish.carfaxtechnicalassignment.requests;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ca.agoldfish.carfaxtechnicalassignment.model.VehicleTest2;

public class VehicleApiClient {

    private static VehicleApiClient instance;
    private MutableLiveData<List<VehicleTest2>> mVehicles;

    public static VehicleApiClient getInstance() {
        if (instance == null)
            instance = new VehicleApiClient();
        return instance;
    }

    private VehicleApiClient() {
        mVehicles = new MutableLiveData<>();
    }

    public LiveData<List<VehicleTest2>> getVehicles() {
        return mVehicles;
    }
}
