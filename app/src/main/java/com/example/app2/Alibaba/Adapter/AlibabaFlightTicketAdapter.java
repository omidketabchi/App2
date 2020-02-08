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

import com.example.app2.Alibaba.AlibabaInformationActivity;
import com.example.app2.Alibaba.Model.AlibabaFlightTicketModel;
import com.example.app2.R;

import java.util.List;

public class AlibabaFlightTicketAdapter extends RecyclerView.Adapter<AlibabaFlightTicketAdapter.AlibabaTicketViewHolder> {

    List<AlibabaFlightTicketModel> models;
    Context context;

    public AlibabaFlightTicketAdapter(Context context, List<AlibabaFlightTicketModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public AlibabaTicketViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.alibaba_datail_item, viewGroup, false);

        return new AlibabaTicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlibabaTicketViewHolder holder, int position) {

        AlibabaFlightTicketModel model = models.get(position);

        holder.id = model.getId();
        holder.txtCapacity.setText(model.getCapacity() + " نفر");
        holder.txtCompany.setText(model.getCompany());
        holder.txtPrice.setText(model.getPriceYoung());
        holder.txtTime.setText(model.getFlightTime());
        holder.txtKing1.setText(model.getKind1());
        holder.txtKing2.setText(model.getKind2());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, AlibabaInformationActivity.class);

                intent.putExtra("model", model);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class AlibabaTicketViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout parent;
        TextView txtPrice;
        TextView txtTime;
        TextView txtCompany;
        TextView txtCapacity;
        TextView txtKing1;
        TextView txtKing2;
        String id;

        public AlibabaTicketViewHolder(@NonNull View itemView) {
            super(itemView);

            txtPrice = itemView.findViewById(R.id.txt_alibabaDetailItem_price);
            txtTime = itemView.findViewById(R.id.txt_alibabaDetailItem_time);
            txtCompany = itemView.findViewById(R.id.txt_alibabaDetailItem_company);
            txtCapacity = itemView.findViewById(R.id.txt_alibabaDetailItem_capacity);
            txtKing1 = itemView.findViewById(R.id.txt_alibabaDetailItem_kind1);
            txtKing2 = itemView.findViewById(R.id.txt_alibabaDetailItem_kind2);
            parent = itemView.findViewById(R.id.rl_alibabaDetailItem_parent);
        }
    }
}
