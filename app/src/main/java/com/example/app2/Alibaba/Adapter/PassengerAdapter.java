package com.example.app2.Alibaba.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.R;

import java.util.List;

public class PassengerAdapter extends RecyclerView.Adapter<PassengerAdapter.PassengerViewHolder> {

    Context context;
    List<String> names;
    OnAdapterNameClick onAdapterNameClick;

    public PassengerAdapter(Context context, List<String> names) {

        this.context = context;
        this.names = names;
    }

    @NonNull
    @Override
    public PassengerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.passenger_item, viewGroup, false);

        return new PassengerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PassengerViewHolder holder, int position) {

        String name = names.get(position);

        holder.txtName.setText(name);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdapterNameClick.onNameClick(name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class PassengerViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        RelativeLayout parent;

        public PassengerViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txt_passengerItem_name);
            parent = (RelativeLayout) itemView.findViewById(R.id.rl_passengerItem_parent);
        }
    }

    public interface OnAdapterNameClick {
        void onNameClick(String name);
    }

    public void setOnAdapterNameClick(OnAdapterNameClick onAdapterNameClick) {
        this.onAdapterNameClick = onAdapterNameClick;
    }
}
