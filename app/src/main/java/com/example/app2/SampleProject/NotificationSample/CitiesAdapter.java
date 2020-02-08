package com.example.app2.SampleProject.NotificationSample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.app2.R;

import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CityViewHolder> {
    private List<City> mCities;
    private OnItemClickListener mOnItemClickListener;

    public CitiesAdapter(final @NonNull List<City> cities,
                         final @NonNull OnItemClickListener onItemClickListener) {
        mCities = cities;
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(final @NonNull ViewGroup parent,
                                             final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_text,
                parent,
                false);
        return new CityViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(final @NonNull CityViewHolder viewHolder,
                                 final int position) {
        viewHolder.mDescription.setText(mCities.get(position).getDescription());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CircleCrop());

        Glide.with(viewHolder.itemView.getContext())
                .load(mCities.get(position).getImageURL())
                .apply(requestOptions)
                .into(viewHolder.mCityImage);
    }

    @Override
    public int getItemCount() {
        return mCities != null ? mCities.size() : 0;
    }
}
