package com.example.app2.Alibaba.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.Alibaba.Model.ProfileModel;
import com.example.app2.R;

import java.util.List;

public class AlibabaProfileAdapter extends RecyclerView.Adapter<AlibabaProfileAdapter.AlibabaProfileViewHolder> {


    Context context;
    List<ProfileModel> profiles;

    public AlibabaProfileAdapter(Context context, List<ProfileModel> profiles) {

        this.context = context;
        this.profiles = profiles;
    }

    @NonNull
    @Override
    public AlibabaProfileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.profile_item, viewGroup, false);

        return new AlibabaProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlibabaProfileViewHolder holder, int position) {

        int row = position + 1;
        ProfileModel profile = profiles.get(position);

        if (row % 2 == 0) {
            holder.parent.setBackgroundColor(ContextCompat.getColor(context, R.color.colorGray200));
        } else {
            holder.parent.setBackgroundColor(ContextCompat.getColor(context, R.color.default_white));
        }

        holder.txtTitle.setText(profile.getTitle());
        holder.txtValue.setText(profile.getValue());
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public class AlibabaProfileViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtValue;
        ConstraintLayout parent;

        public AlibabaProfileViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.txt_profileItem_title);
            txtValue = (TextView) itemView.findViewById(R.id.txt_profileItem_value);
            parent = (ConstraintLayout) itemView.findViewById(R.id.cl_profileItem_parent);
        }
    }
}
