package com.example.app2.CafeBazar.CafeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.CafeBazar.CafeModel.App;
import com.example.app2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context context;
    List<App> apps;

    public SearchAdapter(Context context, List<App> apps) {
        this.context = context;
        this.apps = apps;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.search_row, viewGroup, false);

        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        App app = apps.get(position);

        holder.txtAppName.setText(app.getName());
        Picasso.with(context).load(app.getIcon()).into(holder.imgIcon);
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        ImageView imgIcon;
        TextView txtAppName;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            imgIcon = (ImageView) itemView.findViewById(R.id.img_searchRow_icon);
            txtAppName = (TextView) itemView.findViewById(R.id.txt_searchRow_appName);
        }
    }
}
