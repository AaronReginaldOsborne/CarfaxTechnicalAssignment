package ca.agoldfish.carfaxtechnicalassignment.requests;

import ca.agoldfish.carfaxtechnicalassignment.util.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static VehicleAPI vehicleAPI = retrofit.create(VehicleAPI.class);

    public static VehicleAPI getVehicleAPI(){
        return vehicleAPI;
    }
}
