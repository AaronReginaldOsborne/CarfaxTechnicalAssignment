package ca.agoldfish.carfaxtechnicalassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CarItemAdapter extends RecyclerView.Adapter<CarItemAdapter.carItemViewHolder> {

    private Context mContext;
    private ArrayList<CarItem> mCarItems;

    public CarItemAdapter(Context context, ArrayList<CarItem> carItems){
        mContext = context;
        mCarItems = carItems;
    }

    @NonNull
    @Override
    public carItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.car_page,parent,false);
        return new carItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull carItemViewHolder holder, int i) {
        CarItem carItem = mCarItems.get(i);
        String imageUrl = carItem.get_imageUrl();
        String title = carItem.get_make();
        String subTitle = carItem.get_model();
        String model = carItem.get_model();

        holder.mTextViewTitle.setText(title);
        holder.mTextViewSubTitle.setText(subTitle);

        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mCarItems.size();
    }

    public class carItemViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mTextViewTitle;
        public TextView mTextViewSubTitle;

        public carItemViewHolder(View carItemView){
            super(carItemView);
            mImageView = carItemView.findViewById(R.id.car_IV);
            mTextViewTitle = carItemView.findViewById(R.id.car_title_TV);
            mTextViewSubTitle = carItemView.findViewById(R.id.car_subtitle_TV);
        }
    }
}
