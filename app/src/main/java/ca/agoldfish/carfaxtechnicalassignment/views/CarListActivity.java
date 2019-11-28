package ca.agoldfish.carfaxtechnicalassignment.views;

import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.List;

import ca.agoldfish.carfaxtechnicalassignment.BaseActivity;
import ca.agoldfish.carfaxtechnicalassignment.R;
import ca.agoldfish.carfaxtechnicalassignment.adapters.vehicleAdapter;
import ca.agoldfish.carfaxtechnicalassignment.util.HelperMethods;
import ca.agoldfish.carfaxtechnicalassignment.model.Vehicle;
import ca.agoldfish.carfaxtechnicalassignment.util.Testing;
import ca.agoldfish.carfaxtechnicalassignment.viewmodels.VehicleListViewModel;
public class CarListActivity extends BaseActivity implements vehicleAdapter.OnCarClickListener {

    //tag
    private static final String TAG = "CarListActivity";
    private RecyclerView mRecyclerView;
    private VehicleListViewModel mVehicleListViewModel;

    private vehicleAdapter mVehicleAdapter;
    private List<Vehicle> mVehicles;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cars_listing);

        mVehicleListViewModel = ViewModelProviders.of(this).get(VehicleListViewModel.class);
        mRecyclerView = findViewById(R.id.car_listing_RV);
        mVehicles = new ArrayList<>();
        testRetrofitRequest();

        initRecyclerView();
        subscribeObservers();
    }

    private void initRecyclerView(){
        mVehicleAdapter = new vehicleAdapter(this,this);
        mRecyclerView.setAdapter(mVehicleAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void subscribeObservers() {
        mVehicleListViewModel.getVehicles().observe(this, new Observer<List<Vehicle>>() {
            @Override
            public void onChanged(List<Vehicle> vehicles) {
                //testing
                if (vehicles != null) {
                    Testing.printVehicles(vehicles, TAG);
                    mVehicleAdapter.setVehicles(vehicles);
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

    //Code to setup the RecyclerView and Adapter

    @Override
    public void onCallClick(String phoneNumber) {
        //you can send the phoneNumber instead of the fake number
        HelperMethods.makeCall(this, CarListActivity.this, "1234567890");
    }

    @Override
    public void onItemClick(int adapterPosition, Vehicle vehicle, ImageView mImageView) {
        Intent intent = new Intent(this, CarDetailActivity.class);
        intent.putExtra("carItem_data", vehicle);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                mImageView,
                "imgAnimation");

        startActivity(intent, options.toBundle());
    }
}