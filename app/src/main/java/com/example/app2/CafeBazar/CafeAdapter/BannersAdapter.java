package com.example.app2.CafeBazar.CafeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.CafeBazar.CafeModel.Banner;
import com.example.app2.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BannersAdapter extends RecyclerView.Adapter<BannersAdapter.BannerViewHolder> {

    List<Banner> banners;
    Context context;

    public BannersAdapter(List<Banner> banners, Context context) {
        this.banners = banners;
        this.context = context;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.banner_row,
                parent, false);

        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {

        Banner banner = banners.get(position);

        Picasso.with(context).load(banner.getUrl()).into(holder.img);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, banner.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return banners.size();
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView img;
        LinearLayout parent;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (RoundedImageView) itemView.findViewById(R.id.img_bannerRow);
            parent = (LinearLayout)itemView.findViewById(R.id.linear_bannerRow_parent);
        }
    }
}
