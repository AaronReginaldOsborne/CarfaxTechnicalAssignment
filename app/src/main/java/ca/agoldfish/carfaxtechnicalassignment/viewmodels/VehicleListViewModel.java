package ca.agoldfish.carfaxtechnicalassignment.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ca.agoldfish.carfaxtechnicalassignment.model.VehicleTest2;

public class VehicleListViewModel extends ViewModel {

    private MutableLiveData<List<VehicleTest2>> mVehicles = new MutableLiveData<>();

    public VehicleListViewModel() {
    }

    public LiveData<List<VehicleTest2>> getVehicles(){
        return mVehicles;
    }
}
