package com.example.app2.Alibaba.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.Alibaba.FragmentAlibaba.FragmentDialogLogin;
import com.example.app2.Alibaba.Model.NavigationModel;
import com.example.app2.Alibaba.ProfileActivity;
import com.example.app2.R;

import java.util.List;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavigationViewHoder> {

    OnDialogDismissed onDialogDismissed;
    Context context;
    List<NavigationModel> models;

    public NavigationAdapter(Context context, List<NavigationModel> models) {

        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public NavigationViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_navigation_menu, viewGroup, false);

        return new NavigationViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NavigationViewHoder holder, int position) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);

        NavigationModel model = models.get(position);

        holder.txtTitle.setText(model.getTitle());

        holder.imgIcon.setImageResource(model.getDrawable());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = sharedPreferences.getString("email", "");

                if (holder.txtTitle.getText().equals("پروفایل کاربری")) {
                    if (email.equals("")) {

                        FragmentDialogLogin fragmentDialogLogin = new FragmentDialogLogin();
                        fragmentDialogLogin.show(((AppCompatActivity) context).getSupportFragmentManager(),
                                null);

                        fragmentDialogLogin.setOnSignupClicked(new FragmentDialogLogin.OnSignupClicked() {
                            @Override
                            public void onClicked(String response) {
                                onDialogDismissed.onDismissed(response);
                            }
                        });
                    } else {
                        Intent intent = new Intent(context, ProfileActivity.class);
                        intent.putExtra("email", email);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class NavigationViewHoder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        ImageView imgIcon;
        ConstraintLayout parent;

        public NavigationViewHoder(@NonNull View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.txt_navigationItem_title);
            imgIcon = (ImageView) itemView.findViewById(R.id.img_navigationItem_icon);
            parent = (ConstraintLayout) itemView.findViewById(R.id.cl_navigationItem_parent);
        }
    }

    public interface OnDialogDismissed {
        void onDismissed(String response);
    }

    public void setOnDialogDismissed(OnDialogDismissed onDialogDismissed) {
        this.onDialogDismissed = onDialogDismissed;
    }
}
