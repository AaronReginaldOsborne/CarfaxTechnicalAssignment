package ca.agoldfish.carfaxtechnicalassignment.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ca.agoldfish.carfaxtechnicalassignment.BaseActivity;
import ca.agoldfish.carfaxtechnicalassignment.R;
import ca.agoldfish.carfaxtechnicalassignment.util.HelperMethods;
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
        if (vehicle.getImageCount()>0)
            Picasso.get().load(vehicle.getImages().getLarge()[0]).fit().centerInside().into(imageView);
        title_tv.setText(vehicle.getYear() + " " + vehicle.getMake() + " " + vehicle.getModel());
        rating_rb.setRating(vehicle.getDealer().getDealerReviewRating());
        price_tv.setText(String.format("$%,d", vehicle.getCurrentPrice()));
        milage_tv.setText(String.format("%,d", vehicle.getMileage()));
        location_tv.setText(vehicle.getDealer().getCity()+ " , "+ vehicle.getDealer().getState());
        exterior_tv.setText(vehicle.getExteriorColor());
        interior_tv.setText(vehicle.getInteriorColor());
        drive_tv.setText(vehicle.getDrivetype());
        transmission_tv.setText(vehicle.getTransmission());
        body_tv.setText(vehicle.getBodytype());
        engine_tv.setText(vehicle.getEngine());
        fuel_tv.setText(vehicle.getFuel());

    }


    public void CallDealerPress(View view) {
        //carItem.get_phoneNumber(); send this instead of the phoneNumber
        HelperMethods.makeCall(this, CarDetailActivity.this,"1234567890");
    }

    public void backClicked(View view) {
        onBackPressed();
    }

}
