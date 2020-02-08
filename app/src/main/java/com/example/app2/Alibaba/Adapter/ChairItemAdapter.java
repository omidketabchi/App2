package com.example.app2.Alibaba.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.Alibaba.Model.AlibabaChairModel;
import com.example.app2.R;

import java.util.ArrayList;

public class ChairItemAdapter extends RecyclerView.Adapter<ChairItemAdapter.ChairItemViewHolder> {

    Context context;
    ArrayList<AlibabaChairModel> models;
    int chairNumber = 0;

    OnChairClicked onChairClicked;

    public ChairItemAdapter(Context context, ArrayList<AlibabaChairModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ChairItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.chair_item, viewGroup, false);

        return new ChairItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChairItemViewHolder holder, int position) {

        AlibabaChairModel model = models.get(position * 3);
        holder.txtLeft.setText((position * 3 + 1) + "");

        if (model.getStatus().equals("0")) {
            holder.txtLeft.setBackground(ContextCompat.getDrawable(context, R.drawable.txt_chair_empty_style));
        } else {
            holder.txtLeft.setBackground(ContextCompat.getDrawable(context, R.drawable.txt_chair_style));
        }

        AlibabaChairModel model1 = models.get(position * 3 + 1);
        holder.txtRight.setText((position * 3 + 2) + "");

        if (model1.getStatus().equals("0")) {
            holder.txtRight.setBackground(ContextCompat.getDrawable(context, R.drawable.txt_chair_empty_style));
        } else {
            holder.txtRight.setBackground(ContextCompat.getDrawable(context, R.drawable.txt_chair_style));
        }

        AlibabaChairModel model2 = models.get(position * 3 + 2);
        holder.txtRightOne.setText((position * 3 + 3) + "");

        if (model2.getStatus().equals("0")) {
            holder.txtRightOne.setBackground(ContextCompat.getDrawable(context, R.drawable.txt_chair_empty_style));
        } else {
            holder.txtRightOne.setBackground(ContextCompat.getDrawable(context, R.drawable.txt_chair_style));
        }

        holder.txtLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.txtLeft.getContentDescription().equals("notselected")) {
                    holder.txtLeft.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_selected_chair_style));
                    holder.txtLeft.setContentDescription("selected");
                    onChairClicked.chairClicked(holder.txtLeft.getText().toString());
                } else {
                    holder.txtLeft.setBackground(ContextCompat.getDrawable(context, R.drawable.txt_chair_empty_style));
                    holder.txtLeft.setContentDescription("notselected");
                }
            }
        });

        holder.txtRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.txtRight.getContentDescription().equals("notselected")) {
                    holder.txtRight.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_selected_chair_style));
                    holder.txtRight.setContentDescription("selected");
                    onChairClicked.chairClicked(holder.txtRight.getText().toString());
                } else {
                    holder.txtRight.setBackground(ContextCompat.getDrawable(context, R.drawable.txt_chair_empty_style));
                    holder.txtRight.setContentDescription("notselected");
                }
            }
        });

        holder.txtRightOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.txtRightOne.getContentDescription().equals("notselected")) {
                    holder.txtRightOne.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_selected_chair_style));
                    holder.txtRightOne.setContentDescription("selected");
                    onChairClicked.chairClicked(holder.txtRightOne.getText().toString());
                } else {
                    holder.txtRightOne.setBackground(ContextCompat.getDrawable(context, R.drawable.txt_chair_empty_style));
                    holder.txtRightOne.setContentDescription("notselected");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size() / 3;
    }

    public class ChairItemViewHolder extends RecyclerView.ViewHolder {

        TextView txtLeft;
        TextView txtRight;
        TextView txtRightOne;

        public ChairItemViewHolder(@NonNull View itemView) {
            super(itemView);

            txtLeft = (TextView) itemView.findViewById(R.id.txt_chairItem_left);
            txtRight = (TextView) itemView.findViewById(R.id.txt_chairItem_right);
            txtRightOne = (TextView) itemView.findViewById(R.id.txt_chairItem_rightOne);
        }
    }

    public void setOnChairClicked(OnChairClicked onChairClicked) {
        this.onChairClicked = onChairClicked;
    }

    public interface OnChairClicked {

        void chairClicked(String chairNumber);
    }
}
