package com.example.app2.Alibaba.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.Alibaba.Model.PenaltyModel;
import com.example.app2.R;

import java.util.List;

public class PenaltyAdapter extends RecyclerView.Adapter<PenaltyAdapter.PenaltyViewHolder> {

    Context context;
    List<PenaltyModel> penaltyModels;

    public PenaltyAdapter(Context context, List<PenaltyModel> penaltyModels) {
        this.context = context;
        this.penaltyModels = penaltyModels;
    }

    @NonNull
    @Override
    public PenaltyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.penalty_item,
                viewGroup, false);

        return new PenaltyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenaltyViewHolder holder, int position) {
        PenaltyModel penaltyModel = penaltyModels.get(position);

        holder.txtPenaltyRule.setText(penaltyModel.getRuleTitle());
        holder.txtPercentage.setText(penaltyModel.getPenaltyPercentage());
    }

    @Override
    public int getItemCount() {
        return penaltyModels.size();
    }

    public class PenaltyViewHolder extends RecyclerView.ViewHolder {

        TextView txtPenaltyRule;
        TextView txtPercentage;

        public PenaltyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtPenaltyRule = itemView.findViewById(R.id.txt_penaltyItem_rule);
            txtPercentage = itemView.findViewById(R.id.txt_penaltyItem_percentage);
        }
    }
}
