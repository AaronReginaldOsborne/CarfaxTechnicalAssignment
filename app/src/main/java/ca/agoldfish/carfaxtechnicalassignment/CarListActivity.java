package ca.agoldfish.carfaxtechnicalassignment;

import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.agoldfish.carfaxtechnicalassignment.helper.HelperMethods;
import ca.agoldfish.carfaxtechnicalassignment.model.Vehicle;
import ca.agoldfish.carfaxtechnicalassignment.model.VehicleTest;
import ca.agoldfish.carfaxtechnicalassignment.model.VehicleTest2;
import ca.agoldfish.carfaxtechnicalassignment.requests.ServiceGenerator;
import ca.agoldfish.carfaxtechnicalassignment.requests.VehicleAPI;
import ca.agoldfish.carfaxtechnicalassignment.requests.VehicleResponse.VehicleListingsResponse;
import ca.agoldfish.carfaxtechnicalassignment.requests.VehicleResponse.VehicleResponse;
import ca.agoldfish.carfaxtechnicalassignment.viewmodels.VehicleListViewModel;
import retrofit2.Call;
import retrofit2.Callback;

import static ca.agoldfish.carfaxtechnicalassignment.util.Constants.EXTRA_URL;

public class CarListActivity extends BaseActivity implements CarItemAdapter.OnCarClickListener {

    //tag
    private static final String TAG = "CarListActivity";
    public static final int CALL_DEALER_PERMISSION_CODE = 1;
    private RecyclerView _RecyclerView;
    private CarItemAdapter _CarItemAdapter;
    private ArrayList<Vehicle> _Vehicles;
    private RequestQueue mRequestQueue;

    private VehicleListViewModel mVehicleListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cars_listing);

        mVehicleListViewModel = ViewModelProviders.of(this).get(VehicleListViewModel.class);

        _RecyclerView = findViewById(R.id.car_listing_RV);
        _RecyclerView.setHasFixedSize(true);
        _RecyclerView.setLayoutManager(new LinearLayoutManager(this));

        _Vehicles = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        jsonParse();

        subscribeObservers();
    }

    private void subscribeObservers() {
        mVehicleListViewModel.getVehicles().observe(this, new Observer<List<VehicleTest2>>() {
            @Override
            public void onChanged(List<VehicleTest2> vehicles) {
                //testing
                if (vehicles != null)
                    for (VehicleTest2 vehicle : vehicles) {
                        Log.d(TAG, "onChanged: " + vehicle.getModel());
                    }

            }
        });
    }

    private void testRetrofitRequest(){
        searchVehiclesApi();
    }

    private void searchVehiclesApi() {
        mVehicleListViewModel.searchVehiclesApi();
    }

    private void jsonParse() {

        String url = "https://carfax-for-consumers.firebaseio.com/assignment.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray listings = response.getJSONArray("listings");
                            for (int i = 0; i < listings.length(); ++i) {
                                JSONObject listing = listings.getJSONObject(i);

                                // better way of doing this would be to use GSON and a VM
                                Vehicle vehicle = new Vehicle();
                                int imageCount = listing.getInt("imageCount");
                                if (imageCount > 0)
                                    vehicle.set_imageUrl(listing.getJSONObject("images").getJSONObject("firstPhoto").getString("large"));
                                vehicle.set_id(listing.getString("id"));
                                vehicle.set_year(listing.getString("year"));
                                vehicle.set_make(listing.getString("make"));
                                vehicle.set_model(listing.getString("model"));
                                vehicle.set_trim(listing.getString("trim"));
                                vehicle.set_price(listing.getInt("currentPrice"));
                                vehicle.set_milage(listing.getInt("mileage"));
                                JSONObject dealer = listing.getJSONObject("dealer");
                                vehicle.set_rating((float) dealer.getDouble("dealerAverageRating"));
                                vehicle.set_city(dealer.getString("city"));
                                vehicle.set_state(dealer.getString("state"));
                                vehicle.set_latitude(dealer.getString("latitude"));
                                vehicle.set_longitude(dealer.getString("longitude"));
                                vehicle.set_phoneNumber(dealer.getString("phone"));
                                vehicle.set_interior_color(listing.getString("interiorColor"));
                                vehicle.set_exterior_color(listing.getString("exteriorColor"));
                                vehicle.set_drive_type(listing.getString("drivetype"));
                                vehicle.set_transmision(listing.getString("transmission"));
                                vehicle.set_engine(listing.getString("engine"));
                                vehicle.set_body_type(listing.getString("bodytype"));
                                vehicle.set_fuel(listing.getString("fuel"));

                                // should use RXJava to have live data
                                _Vehicles.add(vehicle);

                            }

                            _CarItemAdapter = new CarItemAdapter(CarListActivity.this, _Vehicles, CarListActivity.this);
                            _RecyclerView.setAdapter(_CarItemAdapter);

                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    //Code to setup the RecyclerView and Adapter

    @Override
    public void onCallClick(String phoneNumber) {
        //you can send the phoneNumber instead of the fake number
        HelperMethods.makeCall(this, CarListActivity.this, "1234567890");
    }

    @Override
    public void onItemClick(int adapterPosition, Vehicle vehicle, ImageView mImageView) {
        Intent intent = new Intent(this, CarDetailActivity.class);

        intent.putExtra(EXTRA_URL, vehicle.get_imageUrl());
        intent.putExtra("carItem_data", vehicle);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                mImageView,
                "imgAnimation");

        startActivity(intent, options.toBundle());
    }

    public void Retrofit(View view) {
        //testing
        testRetrofitRequest();
    }
}