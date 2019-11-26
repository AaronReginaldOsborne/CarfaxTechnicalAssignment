package ca.agoldfish.carfaxtechnicalassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;

import ca.agoldfish.carfaxtechnicalassignment.helper.HelperMethods;
import ca.agoldfish.carfaxtechnicalassignment.model.CarItem;

public class MainActivity extends BaseActivity implements CarItemAdapter.OnCarClickListener {

    public static final String EXTRA_URL = "imageURL";
    public static final int CALL_DEALER_PERMISSION_CODE = 1;


    private RecyclerView _RecyclerView;
    private CarItemAdapter _CarItemAdapter;
    private ArrayList<CarItem> _CarItems;
    private RequestQueue mRequestQueue;

    private int test = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cars_listing);

        setTitle("Carfax");

        _RecyclerView = findViewById(R.id.car_listing_RV);
        _RecyclerView.setHasFixedSize(true);
        _RecyclerView.setLayoutManager(new LinearLayoutManager(this));

        _CarItems = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        jsonParse();
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
                                CarItem carItem = new CarItem();
                                int imageCount = listing.getInt("imageCount");
                                if (imageCount > 0)
                                    carItem.set_imageUrl(listing.getJSONObject("images").getJSONObject("firstPhoto").getString("large"));
                                carItem.set_id(listing.getString("id"));
                                carItem.set_year(listing.getString("year"));
                                carItem.set_make(listing.getString("make"));
                                carItem.set_model(listing.getString("model"));
                                carItem.set_trim(listing.getString("trim"));
                                carItem.set_price(listing.getInt("currentPrice"));
                                carItem.set_milage(listing.getInt("mileage"));
                                JSONObject dealer = listing.getJSONObject("dealer");
                                carItem.set_rating((float) dealer.getDouble("dealerAverageRating"));
                                carItem.set_city(dealer.getString("city"));
                                carItem.set_state(dealer.getString("state"));
                                carItem.set_latitude(dealer.getString("latitude"));
                                carItem.set_longitude(dealer.getString("longitude"));
                                carItem.set_phoneNumber(dealer.getString("phone"));
                                carItem.set_interior_color(listing.getString("interiorColor"));
                                carItem.set_exterior_color(listing.getString("exteriorColor"));
                                carItem.set_drive_type(listing.getString("drivetype"));
                                carItem.set_transmision(listing.getString("transmission"));
                                carItem.set_engine(listing.getString("engine"));
                                carItem.set_body_type(listing.getString("bodytype"));
                                carItem.set_fuel(listing.getString("fuel"));

                                // should use RXJava to have live data
                                _CarItems.add(carItem);

                            }

                            _CarItemAdapter = new CarItemAdapter(MainActivity.this, _CarItems,MainActivity.this);
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
    public void onCallClick(String phoneNumber){
        //you can send the phoneNumber instead of the fake number
        HelperMethods.makeCall(this, MainActivity.this,"1234567890");
    }

    @Override
    public void onItemClick(int adapterPosition, CarItem carItem, ImageView mImageView) {
        Intent intent = new Intent(this, CarDetailActivity.class);

        intent.putExtra(EXTRA_URL, carItem.get_imageUrl());
        intent.putExtra("carItem_data", carItem);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                mImageView,
                "imgAnimation");

        startActivity(intent, options.toBundle());
    }
}