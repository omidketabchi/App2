package com.example.app2.CafeBazar.CafeFragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.BuildConfig;
import com.example.app2.CafeBazar.CafeAdapter.CommentsAdapter;
import com.example.app2.CafeBazar.CafeAdapter.SlidesAdapter;
import com.example.app2.CafeBazar.CafeModel.App;
import com.example.app2.CafeBazar.CafeModel.CommentApp;
import com.example.app2.CafeBazar.Database.SqliteOpenHelper;
import com.example.app2.CafeBazar.Retrofit.ApiClient;
import com.example.app2.CafeBazar.Retrofit.ApiService;
import com.example.app2.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDetail extends Fragment {

    AppCompatRatingBar ratingBar;
    ImageView imgIcon;
    TextView txtAppname;
    TextView txtToolbarAppName;
    TextView txtPayment;
    TextView txtMore;
    TextView txtDeveloper;
    TextView txtOptionDownload;
    TextView txtOptionAverage;
    TextView txtOptionCommentCount;
    TextView txtOptionSize;
    TextView txtOptionCatname;
    TextView txtDescription;
    ImageView imgCatIcon;
    ImageView imgShare;
    ImageView imgFavorite;
    Button btnSetup;
    ProgressBar progressBar;

    View view;

    String id = "";
    String name = "";
    String kind = "";
    String icon = "";

    List<String> slides;
    List<CommentApp> comments;

    App app;
    RecyclerView slidesRecycler;
    RecyclerView commentsRecycler;
    SharedPreferences sharedPreferences;
    SqliteOpenHelper sqLiteOpenHelper;

    Timer timer;


    int stars = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cafe_detail, container, false);


        Bundle bundle = getArguments();

        id = bundle.getString("id");
        name = bundle.getString("name");
        kind = bundle.getString("kind");
        icon = bundle.getString("icon");

        setupViews();

        sqLiteOpenHelper = new SqliteOpenHelper(getContext());
        Cursor cursor = sqLiteOpenHelper.getInfo();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int id_ = cursor.getInt(0);
            int appId = cursor.getInt(1);
            if (appId == Integer.parseInt(id)) {
                imgFavorite.setColorFilter(Color.argb(255, 204, 204, 255));
            }
        }

//        getUniqueAppFromServer();

        getCommentsAppFromServer();

        txtMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();

                bundle.putString("description", app.getDescription());

                FragmentManager manager = ((AppCompatActivity) getContext()).getSupportFragmentManager();

                FragmentTransaction transaction = manager.beginTransaction();

                FragmentDescription fragmentDescription = new FragmentDescription();
                fragmentDescription.setArguments(bundle);
                transaction.replace(R.id.rel_main_parentAllView, fragmentDescription);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ratingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    sharedPreferences = getContext().getSharedPreferences("home", Context.MODE_PRIVATE);
                    String userId = sharedPreferences.getString("userId", "");
                    if (userId.equals("")) {
                        Toast.makeText(getContext(), "لطفا وارد حساب کاربری خود شوید", Toast.LENGTH_SHORT).show();
                    } else {
                        Dialog dialog = new Dialog(getContext());
                        dialog.setContentView(R.layout.dialog);

                        AppCompatRatingBar ratingDialog = (AppCompatRatingBar) dialog.findViewById(R.id.rt_dialog_ratingBar);
                        TextView txtUsername = (TextView) dialog.findViewById(R.id.txt_dialog_userName);
                        TextView txtSave = (TextView) dialog.findViewById(R.id.txt_dialog_saveComment);
                        EditText edtCommentTitle = (EditText) dialog.findViewById(R.id.edt_dialog_commentTitle);

                        txtUsername.setText(app.getUserName());

                        txtSave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int star = Math.round(ratingDialog.getRating());
                                String commentTitle = edtCommentTitle.getText().toString();
                                String appId = app.getId();

                                ApiService service = ApiClient.getClient1().create(ApiService.class);
                                Call<ResponseBody> call = service.addComment(appId, userId, star, commentTitle);


                                call.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                        try {
//                                            Log.i("LOG", "onResponse: " + response.body().string());
                                            String addResult = response.body().string().trim();

                                            if (addResult.equals("ok")) {

                                                Toast.makeText(getContext(), "نظر شما با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                            }
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });

                        dialog.show();
                    }
                }


                return true;
            }
        });

        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("plain/text");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.pax.us/wp-content/uploads/2019/09/AdobeStock_272406883.jpg");
                startActivity(Intent.createChooser(shareIntent, "انتشار برنامه " + name));
            }
        });

        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long res = sqLiteOpenHelper.addAppToFavorite(Integer.parseInt(id), name, icon, kind);
//                getActivity() instead getContext
                Toast.makeText(getContext(), res + "", Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), "برنامه به علاقه مندی ها افزوده شد", Toast.LENGTH_SHORT).show();
                imgFavorite.setColorFilter(Color.argb(255, 204, 204, 255));
            }
        });

        btnSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadApk();
            }
        });

        return view;
    }

    private void setupViews() {

        timer = new Timer();
        slides = new ArrayList<>();

        slides.clear();
        btnSetup = (Button) view.findViewById(R.id.btn_fragmentDetail_setup);
        slidesRecycler = (RecyclerView) view.findViewById(R.id.rv_fragmentDetail_slides);
        commentsRecycler = (RecyclerView) view.findViewById(R.id.rv_fragmentDetail_comments);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_fragmentDetail_progressBar);
        progressBar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        progressBar.setMax(100);
        slidesRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        commentsRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));

        imgIcon = (ImageView) view.findViewById(R.id.img_fragmentDetail_icon);
        txtAppname = (TextView) view.findViewById(R.id.txt_fragmentDetail_appName);
        txtToolbarAppName = (TextView) view.findViewById(R.id.txt_fragmentDetailToolbar_appName);
        txtPayment = (TextView) view.findViewById(R.id.txt_fragmentDetail_appPayment);
        txtDeveloper = (TextView) view.findViewById(R.id.txt_fragmentDetail_developer);
        txtDescription = (TextView) view.findViewById(R.id.txt_fragmentDetail_description);
        txtMore = (TextView) view.findViewById(R.id.txt_fragmentDetail_more);
        ratingBar = (AppCompatRatingBar) view.findViewById(R.id.rt_fragmentDetail_setStar);

        txtOptionDownload = (TextView) view.findViewById(R.id.txt_fragmentDetail_optionDownload);
        txtOptionAverage = (TextView) view.findViewById(R.id.txt_fragmentDetail_average);
        txtOptionCommentCount = (TextView) view.findViewById(R.id.txt_fragmentDetail_commentCount);
        txtOptionSize = (TextView) view.findViewById(R.id.txt_fragmentDetail_size);
        txtOptionCatname = (TextView) view.findViewById(R.id.txt_fragmentDetail_catName);
        imgCatIcon = (ImageView) view.findViewById(R.id.img_fragmentDetail_catImg);
        imgShare = (ImageView) view.findViewById(R.id.img_fragmentDetailToolbar_share);
        imgFavorite = (ImageView) view.findViewById(R.id.img_fragmentDetailToolbar_addFavorite);

        Picasso.with(getContext()).load(icon).into(imgIcon);

        txtAppname.setText(name);
        txtToolbarAppName.setText(name);

        if (kind.equals("free")) {
            txtPayment.setText("+رایگان");
        } else {
            txtPayment.setText("+پرداخت درون برنامه ای");
        }
    }

    private void getUniqueAppFromServer() {

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<App> call = service.getUniqueApp(id);

        call.enqueue(new Callback<App>() {
            @Override
            public void onResponse(Call<App> call, Response<App> response) {
                app = response.body();
                slides = app.getSlides();

                txtDeveloper.setText(app.getUserName());
                txtOptionDownload.setText(app.getDownloads());
                txtOptionAverage.setText("2.3");
                txtOptionCommentCount.setText("70  نظر");
                Picasso.with(getContext()).load(app.getCatIcon()).into(imgCatIcon);
                txtOptionCatname.setText(app.getCatName());
                txtOptionSize.setText(app.getSize() + "مگا بایت");
                txtDescription.setText(app.getDescription());

                slidesRecycler.setAdapter(new SlidesAdapter(slides, getContext()));
            }

            @Override
            public void onFailure(Call<App> call, Throwable t) {

                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCommentsAppFromServer() {

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<App> call = service.getCommentApps(id);

        call.enqueue(new Callback<App>() {
            @Override
            public void onResponse(Call<App> call, Response<App> response) {
                app = response.body();
                comments = app.getComments();
                slides = app.getSlides();

                txtDeveloper.setText(app.getUserName());
                txtOptionDownload.setText(app.getDownloads());

                List<CommentApp> cmnt = app.getComments();

                for (int i = 0; i < cmnt.size(); i++) {
                    stars += Integer.parseInt(cmnt.get(i).getStar());
                }

                float average = (float) stars / cmnt.size();
                txtOptionAverage.setText(String.format("%.2f", average));
                txtOptionCommentCount.setText(cmnt.size() + " نظر");


                Picasso.with(getContext()).load(app.getCatIcon()).into(imgCatIcon);
                txtOptionCatname.setText(app.getCatName());
                txtOptionSize.setText(app.getSize() + "مگا بایت");
                txtDescription.setText(app.getDescription());

                slidesRecycler.setAdapter(new SlidesAdapter(slides, getContext()));
                commentsRecycler.setAdapter(new CommentsAdapter(getContext(), comments));
            }

            @Override
            public void onFailure(Call<App> call, Throwable t) {

                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void downloadApk() {
        progressBar.setVisibility(View.VISIBLE);
        btnSetup.setText("توقف");
        DownloadManager downloadManager = (DownloadManager) getContext().
                getSystemService(Context.DOWNLOAD_SERVICE);

        String url = "http://uupload.ir/filelink/rWtwEfKyj2WS/mt3o_aparat.apk";
        DownloadManager.Request request =
                new DownloadManager.
                        Request(Uri.parse(url));
        String filename = url.substring(url.lastIndexOf("/"));
        request.setTitle("title");
        request.setDescription("description");
        request.setDestinationInExternalPublicDir("/cm", filename);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);

        long id = downloadManager.enqueue(request);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(id);
                Cursor cursor = downloadManager.query(query);

                if (cursor.moveToFirst()) {
                    long downloadedInBytes =
                            cursor.getLong(cursor.getColumnIndex(DownloadManager.
                                    COLUMN_BYTES_DOWNLOADED_SO_FAR));

                    long totalBytes = cursor.getLong(cursor.getColumnIndex(DownloadManager.
                            COLUMN_TOTAL_SIZE_BYTES));

                    int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));

                    int percent = (int) (downloadedInBytes * 100 / totalBytes);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(percent);
                            switch (status) {
                                case DownloadManager.STATUS_SUCCESSFUL:
                                    Toast.makeText(getContext(), "download success", Toast.LENGTH_SHORT).show();


                                    if (Build.VERSION.SDK_INT >= 24) {
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                        Uri uri = FileProvider.getUriForFile(getContext(),
                                                BuildConfig.APPLICATION_ID + ".provider",
                                                new File(Environment.getExternalStorageDirectory(),
                                                        "cm/" + filename));
                                        intent.setDataAndType(uri, "application/vnd.android.package-archive");


                                        getActivity().startActivity(intent);
                                    } else {
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setDataAndType(Uri.fromFile(new File(
                                                        Environment.getExternalStorageDirectory() + "/cm/" + filename)),
                                                "application/vnd.android.package-archive");

                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        getActivity().startActivity(intent);
                                    }

                                    timer.purge();
                                    timer.cancel();
                                    progressBar.setVisibility(View.GONE);
                                    btnSetup.setText("نصب");
                                    break;
                            }
                        }
                    });
                }
            }
        }, 0, 1000);
    }
}


