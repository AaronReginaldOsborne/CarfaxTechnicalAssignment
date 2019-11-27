package ca.agoldfish.carfaxtechnicalassignment.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ca.agoldfish.carfaxtechnicalassignment.model.Vehicle;
import ca.agoldfish.carfaxtechnicalassignment.repositories.VehicleRepository;

public class VehicleListViewModel extends ViewModel {

    private VehicleRepository mVehicleRepository;

    public VehicleListViewModel() {
        mVehicleRepository = VehicleRepository.getInstance();
    }

    public LiveData<List<Vehicle>> getVehicles() {
        return mVehicleRepository.getVehicles();
    }

    public void searchVehiclesApi(){
        mVehicleRepository.searchVehiclesApi();
    }
}
