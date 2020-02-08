package com.example.app2.Alibaba.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.R;

import java.util.List;

public class AlibabaCitiesAdapter extends RecyclerView.Adapter<AlibabaCitiesAdapter.AlibabaCitiesViewHolder> {


    List<String> cities;

    private OnCitySelected onCitySelected;

    public AlibabaCitiesAdapter(List<String> cities, OnCitySelected onCitySelected) {

        this.onCitySelected = onCitySelected;
        this.cities = cities;
    }

    @NonNull
    @Override
    public AlibabaCitiesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.city_item, viewGroup, false);

        return new AlibabaCitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlibabaCitiesViewHolder holder, int position) {
        String city = cities.get(position);
        holder.txtCity.setText(city);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCitySelected.onSelected(city);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class AlibabaCitiesViewHolder extends RecyclerView.ViewHolder {

        TextView txtCity;
        RelativeLayout parent;

        public AlibabaCitiesViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCity = (TextView) itemView.findViewById(R.id.txt_cityItem_title);
            parent = (RelativeLayout) itemView.findViewById(R.id.rel_cityItem_parent);
        }
    }

    public interface OnCitySelected {
        void onSelected(String city);
    }
}
