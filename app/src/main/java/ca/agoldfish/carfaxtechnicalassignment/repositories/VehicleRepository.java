package ca.agoldfish.carfaxtechnicalassignment.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ca.agoldfish.carfaxtechnicalassignment.model.VehicleTest2;
import ca.agoldfish.carfaxtechnicalassignment.requests.VehicleApiClient;

public class VehicleRepository {

    private static VehicleRepository instance;
    private VehicleApiClient mVehicleApiClient;

    public static VehicleRepository getInstance() {
        if (instance == null)
            instance = new VehicleRepository();
        return instance;
    }

    private VehicleRepository() {
        mVehicleApiClient = VehicleApiClient.getInstance();
    }

    public LiveData<List<VehicleTest2>> getVehicles() {
        return mVehicleApiClient.getVehicles();
    }
}
