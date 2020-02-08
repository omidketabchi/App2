package com.example.app2.Alibaba.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.Alibaba.AlibabaBusInformationActivity;
import com.example.app2.Alibaba.Model.AlibabaBusTicketModel;
import com.example.app2.Alibaba.Model.AlibabaChairModel;
import com.example.app2.R;

import java.util.ArrayList;
import java.util.List;

public class AlibabaBusTicketAdapter extends RecyclerView.Adapter<AlibabaBusTicketAdapter.BusTicketViewHolder> {

    Context context;
    List<AlibabaBusTicketModel> models;

    public AlibabaBusTicketAdapter(Context context, List<AlibabaBusTicketModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public BusTicketViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.bus_item, viewGroup, false);

        return new BusTicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusTicketViewHolder holder, int position) {

        AlibabaBusTicketModel model = models.get(position);

        holder.txtType.setText(model.getType());
        holder.txtSource.setText(model.getSource());
        holder.txtSourceTerminal.setText(model.getSourceTerminal());
        holder.txtDestinationTerminal.setText(model.getDestinationTerminal());
        holder.txtCapacity.setText("ظرفیت موجود: " + model.getCapacity() + " نفر");
        holder.txtPrice.setText(model.getPrice());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AlibabaBusInformationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("model", model);
                List<AlibabaChairModel> mm =  model.getChairModels();
                intent.putParcelableArrayListExtra("chairs", (ArrayList<? extends Parcelable>) model.getChairModels());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class BusTicketViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;
        TextView txtSource;
        TextView txtSourceTerminal;
        TextView txtDestinationTerminal;
        TextView txtCapacity;
        TextView txtPrice;
        RelativeLayout parent;

        public BusTicketViewHolder(@NonNull View itemView) {
            super(itemView);

            txtType = (TextView) itemView.findViewById(R.id.txt_busItem_type);
            txtSource = (TextView) itemView.findViewById(R.id.txt_busItem_source);
            txtSourceTerminal = (TextView) itemView.findViewById(R.id.txt_busItem_sourceTerminal);
            txtDestinationTerminal = (TextView) itemView.findViewById(R.id.txt_busItem_destinationTerminal);
            txtCapacity = (TextView) itemView.findViewById(R.id.txt_busItem_capacity);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_busItem_price);
            parent = (RelativeLayout) itemView.findViewById(R.id.rl_busItem_parent);
        }
    }
}
