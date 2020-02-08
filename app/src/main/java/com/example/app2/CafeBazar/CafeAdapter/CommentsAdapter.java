package com.example.app2.CafeBazar.CafeAdapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.CafeBazar.CafeFragment.FragmentComments;
import com.example.app2.CafeBazar.CafeModel.CommentApp;
import com.example.app2.R;

import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

    List<CommentApp> commentApps;
    Context context;

    public CommentsAdapter(Context context, List<CommentApp> commentApps) {

        this.context = context;
        this.commentApps = commentApps;
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cafe_comment_row,
                parent, false);

        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {

        CommentApp commentApp = commentApps.get(position);

        holder.ratingBar.setRating(Float.valueOf(commentApp.getStar()));
        holder.txtUsername.setText(commentApp.getUserName());
        holder.txtDescription.setText(commentApp.getTitle());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = ((AppCompatActivity) context).
                        getSupportFragmentManager();

                Bundle bundle = new Bundle();
                bundle.putString("id", commentApp.getAppId());
//                bundle.putParcelableArrayList("comments", (ArrayList<? extends Parcelable>) commentApps);
                FragmentComments fragmentComments = new FragmentComments();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.rel_main_parentAllView, fragmentComments);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return commentApps.size();
    }

    public class CommentsViewHolder extends RecyclerView.ViewHolder {

        TextView txtUsername;
        TextView txtDescription;
        AppCompatRatingBar ratingBar;
        RelativeLayout parent;

        public CommentsViewHolder(@NonNull View itemView) {

            super(itemView);

            txtUsername = (TextView) itemView.findViewById(R.id.txt_commentRow_userName);
            txtDescription = (TextView) itemView.findViewById(R.id.txt_commentRow_description);
            ratingBar = (AppCompatRatingBar) itemView.findViewById(R.id.rt_commentRow_ratingBar);
            parent = (RelativeLayout) itemView.findViewById(R.id.rl_commentRow_parent);
        }
    }
}
