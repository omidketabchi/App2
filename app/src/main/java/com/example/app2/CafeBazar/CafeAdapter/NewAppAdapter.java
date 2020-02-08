package com.example.app2.CafeBazar.CafeAdapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.CafeBazar.CafeFragment.FragmentDetail;
import com.example.app2.CafeBazar.CafeModel.App;
import com.example.app2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewAppAdapter extends RecyclerView.Adapter<NewAppAdapter.NewAppViewHolder> {

    List<App> apps;
    Context context;

    public NewAppAdapter(List<App> apps, Context context) {

        this.apps = apps;
        this.context = context;
    }

    @NonNull
    @Override
    public NewAppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.new_app_row,
                parent, false);

        return new NewAppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewAppViewHolder holder, int position) {

        App app = apps.get(position);

        Picasso.with(context).load(app.getIcon()).into(holder.img);
        holder.txtTitle.setText(app.getName());

        if (app.getKind().equals("free")) {
            holder.imgCoin.setVisibility(View.GONE);
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();

                bundle.putString("id", app.getId());
                bundle.putString("kind", app.getKind());
                bundle.putString("name", app.getName());
                bundle.putString("icon", app.getIcon());

                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();

                FragmentTransaction transaction = manager.beginTransaction();

                FragmentDetail fragmentDetail = new FragmentDetail();
                fragmentDetail.setArguments(bundle);
                transaction.replace(R.id.rel_main_parentAllView, fragmentDetail);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public class NewAppViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout parent;
        ImageView img;
        TextView txtTitle;
        ImageView imgCoin;

        public NewAppViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.rel_newApp_parent);
            img = itemView.findViewById(R.id.img_newAppRow);
            txtTitle = itemView.findViewById(R.id.txt_newApp_title);
            imgCoin = itemView.findViewById(R.id.img_newAppRow_coin);
        }
    }
}
