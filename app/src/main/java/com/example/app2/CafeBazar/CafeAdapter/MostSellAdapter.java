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

import java.util.ArrayList;
import java.util.List;

public class MostSellAdapter extends RecyclerView.Adapter<MostSellAdapter.MostSellViewHolder> {

    List<App> apps;
    Context context;

    public MostSellAdapter(Context context/*, List<App> apps*/) {
        this.context = context;
        this.apps = new ArrayList<>();
//        this.apps = apps;
    }

    @NonNull
    @Override
    public MostSellViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.cafe_most_sell_recycler,
                viewGroup, false);

        return new MostSellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MostSellViewHolder holder, int position) {
        App app = apps.get(position);
        holder.txtAppName.setText(app.getName());
        holder.txtKind.setText(app.getKind());

        Picasso.with(context).load(app.getIcon()).into(holder.imgSlide);
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public void addData(List<App> newApps) {
        this.apps.addAll(newApps);
        notifyDataSetChanged();
    }

    public class MostSellViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSlide;
        TextView txtAppName;
        TextView txtKind;

        public MostSellViewHolder(@NonNull View itemView) {
            super(itemView);

            imgSlide = (ImageView) itemView.findViewById(R.id.img_fragmentMostSell_appIcon);
            txtAppName = (TextView) itemView.findViewById(R.id.txt_fragmentMostsell_appName);
            txtKind = (TextView) itemView.findViewById(R.id.txt_fragmentMostSell_kind);

        }
    }
}
