package com.example.app2.CafeBazar.CafeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.CafeBazar.CafeModel.Cat;
import com.example.app2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {

    Context context;
    List<Cat> cats;

    public CatAdapter(Context context, List<Cat> cats) {

        this.context = context;
        this.cats = cats;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.cat_row,
                viewGroup, false);

        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {

        Cat cat = cats.get(position);
        Picasso.with(context).load(cat.getCatIcon()).into(holder.imgIcon);
        holder.txtTitle.setText(cat.getCatName());
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder {

        ImageView imgIcon;
        TextView txtTitle;
        RelativeLayout parent;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = (RelativeLayout) itemView.findViewById(R.id.rl_fragmentGameApp_parent);
            imgIcon = (ImageView) itemView.findViewById(R.id.img_fragmentGameApp_icon);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_fragmentGameApp_title);
        }
    }
}
