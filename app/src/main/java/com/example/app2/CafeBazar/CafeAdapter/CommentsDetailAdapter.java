package com.example.app2.CafeBazar.CafeAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.CafeBazar.CafeModel.CommentApp;
import com.example.app2.CafeBazar.Retrofit.ApiClient;
import com.example.app2.CafeBazar.Retrofit.ApiService;
import com.example.app2.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsDetailAdapter extends RecyclerView.Adapter<CommentsDetailAdapter.CommentsDetailViewHolder> {

    List<CommentApp> commentApps;
    Context context;
    SharedPreferences sharedPreferences;
    String userId;

    public CommentsDetailAdapter(Context context, List<CommentApp> commentApps) {
        this.context = context;
        this.commentApps = commentApps;

        sharedPreferences = context.getSharedPreferences("home", Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("userId", "");
    }

    @NonNull
    @Override
    public CommentsDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cafe_comment_row_detail,
                parent, false);

        return new CommentsDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsDetailViewHolder holder, int position) {

        CommentApp comment = commentApps.get(position);

        holder.txtUsername.setText(comment.getUserName());
        holder.ratingBar.setRating(Float.parseFloat(comment.getStar()));
        holder.txtCommentTitle.setText(comment.getTitle());
        holder.txtLikeCount.setText(comment.getLike());
        holder.txtDislikeCount.setText(comment.getDislike());

        holder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ApiService service = ApiClient.getClient1().create(ApiService.class);
                Call<ResponseBody> call = service.setVote("like",
                        userId,
                        comment.getId());

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String like = response.body().string().trim();
                            if (!like.equals("شما قبلا به این برنامه نظر داده اید")) {
                                int likeCount = Integer.parseInt(holder.txtLikeCount.getText().toString());
                                holder.txtLikeCount.setText(String.valueOf(likeCount++));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });

        holder.imgDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService service = ApiClient.getClient1().create(ApiService.class);
                Call<ResponseBody> call = service.setVote("dislike",
                        userId,
                        comment.getId());

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String dislike = response.body().string().trim();

                            if (!dislike.equals("شما قبلا به این برنامه نظر داده اید")) {
                                int dislikeCount = Integer.parseInt(holder.txtDislikeCount.getText().toString());
                                holder.txtDislikeCount.setText(String.valueOf(dislikeCount++));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return commentApps.size();
    }

    public class CommentsDetailViewHolder extends RecyclerView.ViewHolder {

        ImageView imgLike;
        ImageView imgDislike;

        TextView txtLikeCount;
        TextView txtDislikeCount;
        TextView txtUsername;
        TextView txtCommentTitle;
        AppCompatRatingBar ratingBar;

        public CommentsDetailViewHolder(@NonNull View itemView) {
            super(itemView);

            imgLike = (ImageView) itemView.findViewById(R.id.img_commentDetailRow_like);
            imgDislike = (ImageView) itemView.findViewById(R.id.img_commentDetailRow_dislike);
            txtLikeCount = (TextView) itemView.findViewById(R.id.txt_commentDetailRow_likeCount);
            txtDislikeCount = (TextView) itemView.findViewById(R.id.txt_commentDetailRow_dislikeCount);
            txtUsername = (TextView) itemView.findViewById(R.id.txt_commentDetailRow_userName);
            txtCommentTitle = (TextView) itemView.findViewById(R.id.txt_commentDetailRow_description);
            ratingBar = (AppCompatRatingBar) itemView.findViewById(R.id.rt_commentDetailRow_ratingBar);

        }
    }
}
