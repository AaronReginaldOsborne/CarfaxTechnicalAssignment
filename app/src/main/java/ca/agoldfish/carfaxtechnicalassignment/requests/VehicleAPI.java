package ca.agoldfish.carfaxtechnicalassignment.requests;

import ca.agoldfish.carfaxtechnicalassignment.requests.VehicleResponse.VehicleListingsResponse;
import ca.agoldfish.carfaxtechnicalassignment.requests.VehicleResponse.VehicleResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface VehicleAPI {

    //set up for later CRUD functions
    @GET("assignment.json")
    Call<VehicleListingsResponse> getVehicles();

}
