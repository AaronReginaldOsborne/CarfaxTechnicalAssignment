package ca.agoldfish.carfaxtechnicalassignment.adapters;

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

import java.util.List;

import ca.agoldfish.carfaxtechnicalassignment.R;
import ca.agoldfish.carfaxtechnicalassignment.model.Vehicle;

public class vehicleAdapter extends RecyclerView.Adapter<vehicleAdapter.carItemViewHolder> {

    private Context mContext;
    private List<Vehicle> mVehicles;
    private OnCarClickListener mOnCarClickListener;

    public interface OnCarClickListener {
        void onItemClick(int adapterPosition, Vehicle vehicle, ImageView mImageView);

        void onCallClick(String phoneNumber);

    }

    public vehicleAdapter(Context context, OnCarClickListener onCarClickListener) {
        mContext = context;
        mOnCarClickListener = onCarClickListener;
    }

    @NonNull
    @Override
    public carItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.car_card, parent, false);
        return new carItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final carItemViewHolder holder, int i) {
        final Vehicle vehicle = mVehicles.get(i);
        String imageUrl = "";
        if (vehicle.getImageCount() > 0)
            imageUrl = vehicle.getImages().getLarge()[0];
        String year = vehicle.getYear();
        String make = vehicle.getMake();
        String model = vehicle.getModel();
        int price = vehicle.getCurrentPrice();
        int milage = vehicle.getMileage();

        //check if the image Url is blank
        if (!imageUrl.equals(""))
            Picasso.get().load(imageUrl).fit().centerInside().into(holder._car_img);

        holder._title_tv.setText(year + " " + make + " " + model);
        holder._price_tv.setText(String.format("$%,d", price) + "");
        holder._mileage.setText(String.format("%,d", milage) + " Miles");
        holder._location.setText(vehicle.getDealer().getCity() + " , " + vehicle.getDealer().getState());

        holder._call_dealer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnCarClickListener.onCallClick(vehicle.getDealer().getPhone());
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnCarClickListener.onItemClick(holder.getAdapterPosition(), vehicle, holder._car_img);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mVehicles != null)
            return mVehicles.size();
        return 0;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        mVehicles = vehicles;
        notifyDataSetChanged();
    }

    public class carItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView _car_img;
        public TextView _title_tv;
        public TextView _price_tv;
        public TextView _mileage;
        public TextView _location;
        public Button _call_dealer;

        public carItemViewHolder(View carItemView) {
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
