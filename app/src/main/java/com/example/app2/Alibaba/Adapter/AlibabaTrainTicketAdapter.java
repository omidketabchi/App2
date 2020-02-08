package com.example.app2.Alibaba.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.Alibaba.AlibabaTrainInformationActivity;
import com.example.app2.Alibaba.Model.AlibabaTrainTicketModel;
import com.example.app2.R;

import java.util.List;

public class AlibabaTrainTicketAdapter extends RecyclerView.Adapter<AlibabaTrainTicketAdapter.TrainTicketViewHolder> {

    Context context;
    List<AlibabaTrainTicketModel> models;

    public AlibabaTrainTicketAdapter(Context context, List<AlibabaTrainTicketModel> models) {

        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public TrainTicketViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.train_item,
                viewGroup, false);

        return new TrainTicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainTicketViewHolder holder, int position) {

        AlibabaTrainTicketModel model = models.get(position);

        holder.txtEndTime.setText(model.getEndTime());
        holder.txtStartTime.setText(model.getStartTime());
        holder.txtType.setText("::" + model.getType());
        holder.txtPrice.setText(model.getPrice());
        holder.txtCapacity.setText(model.getCapacity());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AlibabaTrainInformationActivity.class);
                intent.putExtra("model", model);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class TrainTicketViewHolder extends RecyclerView.ViewHolder {

        TextView txtStartTime;
        TextView txtEndTime;
        TextView txtType;
        TextView txtPrice;
        TextView txtCapacity;
        RelativeLayout parent;

        public TrainTicketViewHolder(@NonNull View itemView) {
            super(itemView);

            txtStartTime = (TextView) itemView.findViewById(R.id.txt_trainItem_startTime);
            txtEndTime = (TextView) itemView.findViewById(R.id.txt_trainItem_endTime);
            txtType = (TextView) itemView.findViewById(R.id.txt_trainItem_type);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_trainItem_price);
            txtCapacity = (TextView) itemView.findViewById(R.id.txt_trainItem_capacity);
            parent = (RelativeLayout) itemView.findViewById(R.id.rl_trainItem_parent);
        }
    }
}
