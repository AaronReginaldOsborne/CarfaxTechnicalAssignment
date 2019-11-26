package ca.agoldfish.carfaxtechnicalassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ca.agoldfish.carfaxtechnicalassignment.model.Vehicle;

public class CarItemAdapter extends RecyclerView.Adapter<CarItemAdapter.carItemViewHolder> {

    private Context _Context;
    private ArrayList<Vehicle> _Vehicles;
    private OnCarClickListener _onCarClickListener;

    public interface OnCarClickListener {
        void onItemClick(int adapterPosition, Vehicle vehicle, ImageView mImageView);
        void onCallClick(String phoneNumber);

    }

    public CarItemAdapter(Context context, ArrayList<Vehicle> vehicles, OnCarClickListener onCarClickListener){
        _Context = context;
        _Vehicles = vehicles;
        _onCarClickListener = onCarClickListener;
    }

    @NonNull
    @Override
    public carItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(_Context).inflate(R.layout.car_card,parent,false);
        return new carItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final carItemViewHolder holder, int i) {
        final Vehicle vehicle = _Vehicles.get(i);
        String imageUrl = vehicle.get_imageUrl();
        String year = vehicle.get_year();
        String make = vehicle.get_make();
        String model = vehicle.get_model();
        int price = vehicle.get_price();
        int milage = vehicle.get_milage();

        //check if the image Url is blank
        if(!imageUrl.equals(""))
            Picasso.get().load(imageUrl).fit().centerInside().into(holder._car_img);

        holder._title_tv.setText(year + " " + make + " " + model);
        holder._price_tv.setText(String.format("$%,d",price)+"");
        holder._mileage.setText(String.format("%,d",milage)+" Miles");
        holder._location.setText(vehicle.get_city()+ " , "+ vehicle.get_state());

        holder._call_dealer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _onCarClickListener.onCallClick(vehicle.get_phoneNumber());
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _onCarClickListener.onItemClick(holder.getAdapterPosition(), vehicle, holder._car_img);
            }
        });
    }

    @Override
    public int getItemCount() {
        return _Vehicles.size();
    }

    public class carItemViewHolder extends RecyclerView.ViewHolder{

        public ImageView _car_img;
        public TextView _title_tv;
        public TextView _price_tv;
        public TextView _mileage;
        public TextView _location;
        public Button _call_dealer;

        public carItemViewHolder(View carItemView){
            super(carItemView);
            _car_img = carItemView.findViewById(R.id.car_img_iv);
            _title_tv = carItemView.findViewById(R.id.car_title_tv);
            _price_tv = carItemView.findViewById(R.id.car_price_tv);
            _mileage = carItemView.findViewById(R.id.car_milage_tv);
            _location = carItemView.findViewById(R.id.car_location_tv);
            _call_dealer = carItemView.findViewById(R.id.car_call_dealer_btn);
        }
    }
}
