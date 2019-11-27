package ca.agoldfish.carfaxtechnicalassignment.repositories;

import androidx.lifecycle.LiveData;

import java.util.List;

import ca.agoldfish.carfaxtechnicalassignment.model.Vehicle;
import ca.agoldfish.carfaxtechnicalassignment.requests.VehicleApiClient;

//this class is for when we have two or more ways of getting the data for example we grab the data from the API
//We can also store the data into the SQLite within the phone storage so they can have access to the data while offline
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

    public LiveData<List<Vehicle>> getVehicles() {
        return mVehicleApiClient.getVehicles();
    }

    //add search in the future here if we add multi pages
    public void searchVehiclesApi(){
        mVehicleApiClient.searchVehiclesApi();
    }
}
