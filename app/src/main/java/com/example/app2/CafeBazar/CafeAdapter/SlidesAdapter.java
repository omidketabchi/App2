package com.example.app2.CafeBazar.CafeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SlidesAdapter extends RecyclerView.Adapter<SlidesAdapter.SlidesViewHolder> {

    List<String> slides;
    Context context;

    public SlidesAdapter(List<String> slides, Context context) {
        this.context = context;
        this.slides = slides;
    }

    @NonNull
    @Override
    public SlidesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cafe_slides_row, parent, false);

        return new SlidesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlidesViewHolder holder, int position) {

        String imageSlide = slides.get(position);
        Picasso.with(context).load(imageSlide).into(holder.imgSlide);
    }

    @Override
    public int getItemCount() {
        return slides.size();
    }

    public class SlidesViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSlide;

        public SlidesViewHolder(@NonNull View itemView) {
            super(itemView);

            imgSlide = (ImageView)itemView.findViewById(R.id.img_slidesRow_slide);
        }
    }
}
