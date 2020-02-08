package com.example.app2.Alibaba.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.Alibaba.Model.AlibabaHotelModel;
import com.example.app2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HotelItemAdapter extends RecyclerView.Adapter<HotelItemAdapter.HotelItemViewHolder> {

    Context context;
    ArrayList<AlibabaHotelModel> hotels;

    public HotelItemAdapter(Context context, ArrayList<AlibabaHotelModel> hotels) {

        this.context = context;
        this.hotels = hotels;
    }

    @NonNull
    @Override
    public HotelItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.show_hotel_list_item, viewGroup, false);

        return new HotelItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelItemViewHolder holder, int position) {

        AlibabaHotelModel hotel = hotels.get(position);

        Picasso.with(context).load(hotel.getImage()).into(holder.imageView);
        holder.txtName.setText(hotel.getName());
        holder.txtPrice.setText(hotel.getPrice());
        holder.rating.setRating(Float.parseFloat(hotel.getStar()));

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public class HotelItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txtName;
        TextView txtPrice;
        CardView parent;
        AppCompatRatingBar rating;

        public HotelItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.img_showHotelList_image);
            txtName = (TextView) itemView.findViewById(R.id.txt_showHotelList_name);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_showHotelList_price);
            rating = (AppCompatRatingBar) itemView.findViewById(R.id.rt_showHotelList_ratingBar);
            parent = (CardView) itemView.findViewById(R.id.cv_showHotelList_parent);
        }
    }
}
