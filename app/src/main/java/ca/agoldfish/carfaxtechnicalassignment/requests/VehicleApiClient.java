package ca.agoldfish.carfaxtechnicalassignment.requests;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import ca.agoldfish.carfaxtechnicalassignment.AppExecutors;
import ca.agoldfish.carfaxtechnicalassignment.model.VehicleTest2;
import ca.agoldfish.carfaxtechnicalassignment.requests.VehicleResponse.VehicleListingsResponse;
import ca.agoldfish.carfaxtechnicalassignment.requests.VehicleResponse.VehicleResponse;
import retrofit2.Call;
import retrofit2.Response;

import static ca.agoldfish.carfaxtechnicalassignment.util.Constants.NETWORK_TIMEOUT;

public class VehicleApiClient {

    private static final String TAG = "VehicleApiClient";

    private static VehicleApiClient instance;
    private MutableLiveData<List<VehicleTest2>> mVehicles;
    private RetrieveVehiclesRunnable mRetrieveVehiclesRunnable;

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

    public void searchVehiclesApi() {

        if (mRetrieveVehiclesRunnable != null)
            mRetrieveVehiclesRunnable = null;

        mRetrieveVehiclesRunnable = new RetrieveVehiclesRunnable();

        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveVehiclesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {

                // let the user know its timed out
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    // this will be for the search query
    private class RetrieveVehiclesRunnable implements Runnable {

        boolean cancelRequest;

        public RetrieveVehiclesRunnable() {
            this.cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getVehicles().execute();

                if (cancelRequest)
                    return;

                if (response.code() == 200) {
                    List<VehicleTest2> list = new ArrayList<>(((VehicleListingsResponse) response.body()).getVehicles());

                    //set value on a background thread
                    mVehicles.postValue(list);
                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error);
                    mVehicles.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mVehicles.postValue(null);
            }
        }

        // Call to the api
        private Call<VehicleListingsResponse> getVehicles() {
            return ServiceGenerator.getVehicleAPI().getVehicles();
        }

        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: canceling the search request.");
            cancelRequest = true;
        }
    }
}
