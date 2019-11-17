package ca.agoldfish.carfaxtechnicalassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CarItemAdapter _CarItemAdapter;
    private ArrayList<CarItem> _CarItems;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cars_listing);

        mRecyclerView = findViewById(R.id.car_listing_RV);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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
                            for(int i = 0;i<listings.length();++i){
                                JSONObject listing = listings.getJSONObject(i);

                                // better way of doing this would be to use GSON and a VM
                                CarItem carItem = new CarItem();
                                carItem.set_imageUrl(listing.getJSONObject("images").getJSONObject("firstPhoto").getString("large"));
                                carItem.set_year(listing.getString("year"));
                                carItem.set_make(listing.getString("make"));
                                carItem.set_model(listing.getString("model"));
                                carItem.set_trim(listing.getString("trim"));
                                carItem.set_price(listing.getInt("currentPrice"));
                                carItem.set_milage(listing.getInt("mileage"));
                                JSONObject dealer = listing.getJSONObject("dealer");
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

                                // should use RXJava to have live data
                                _CarItems.add(carItem);

                            }


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
}