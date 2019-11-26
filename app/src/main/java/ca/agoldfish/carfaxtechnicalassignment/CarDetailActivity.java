package ca.agoldfish.carfaxtechnicalassignment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ca.agoldfish.carfaxtechnicalassignment.helper.HelperMethods;
import ca.agoldfish.carfaxtechnicalassignment.model.Vehicle;

public class CarDetailActivity extends BaseActivity {

    Vehicle vehicle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);
        vehicle = getIntent().getParcelableExtra("carItem_data");


        //TODO: using kotlin will get rid of this
        ImageView imageView = findViewById(R.id.image_cd_IV);
        TextView title_tv = findViewById(R.id.title_cd_TV);
        RatingBar rating_rb = findViewById(R.id.ratingBar);
        TextView price_tv = findViewById(R.id.price_cd_TV);
        TextView milage_tv = findViewById(R.id.mileage_cd_tv);
        TextView location_tv = findViewById(R.id.location_cd_tv);
        TextView exterior_tv = findViewById(R.id.exterior_cd_tv);
        TextView interior_tv = findViewById(R.id.interior_cd_tv);
        TextView drive_tv = findViewById(R.id.drive_cd_tv);
        TextView transmission_tv = findViewById(R.id.trans_cd_tv);
        TextView body_tv = findViewById(R.id.body_cd_tv);
        TextView engine_tv = findViewById(R.id.engine_cd_tv);
        TextView fuel_tv = findViewById(R.id.fuel_cd_tv);

        //set values
        if (!vehicle.get_imageUrl().equals(""))
            Picasso.get().load(vehicle.get_imageUrl()).fit().centerInside().into(imageView);
        title_tv.setText(vehicle.get_year() + " " + vehicle.get_make() + " " + vehicle.get_model());
        rating_rb.setRating(vehicle.get_rating());
        price_tv.setText(String.format("$%,d", vehicle.get_price()));
        milage_tv.setText(String.format("%,d", vehicle.get_milage()));
        location_tv.setText(vehicle.get_city()+ " , "+ vehicle.get_state());
        exterior_tv.setText(vehicle.get_exterior_color());
        interior_tv.setText(vehicle.get_interior_color());
        drive_tv.setText(vehicle.get_drive_type());
        transmission_tv.setText(vehicle.get_transmision());
        body_tv.setText(vehicle.get_body_type());
        engine_tv.setText(vehicle.get_engine());
        fuel_tv.setText(vehicle.get_fuel());

    }


    public void CallDealerPress(View view) {
        //carItem.get_phoneNumber(); send this instead of the phoneNumber
        HelperMethods.makeCall(this, CarDetailActivity.this,"1234567890");
    }

    public void backClicked(View view) {
        onBackPressed();
    }

}
