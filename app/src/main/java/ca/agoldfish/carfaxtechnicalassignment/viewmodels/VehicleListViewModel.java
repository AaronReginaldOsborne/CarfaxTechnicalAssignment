package ca.agoldfish.carfaxtechnicalassignment.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ca.agoldfish.carfaxtechnicalassignment.model.VehicleTest2;
import ca.agoldfish.carfaxtechnicalassignment.repositories.VehicleRepository;

public class VehicleListViewModel extends ViewModel {

    private VehicleRepository mVehicleRepository;

    public VehicleListViewModel() {
        mVehicleRepository = VehicleRepository.getInstance();
    }

    public LiveData<List<VehicleTest2>> getVehicles() {
        return mVehicleRepository.getVehicles();
    }
}
