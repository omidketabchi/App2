package com.example.app2.CafeBazar.CafeFragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app2.CafeBazar.CafeModel.InstalledPackage;
import com.example.app2.CafeBazar.Retrofit.ApiClient;
import com.example.app2.CafeBazar.Retrofit.ApiService;
import com.example.app2.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentApps extends Fragment {

    TextView txtFavorites;
    TextView txtUpdates;

    List<InstalledPackage> installedPackages;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_cafe_apps, container, false);

            setupViews();
        }

        return view;
    }

    private void setupViews() {

        installedPackages = new ArrayList<>();
        txtFavorites = (TextView) view.findViewById(R.id.txt_fragmentApps_favorites);
        txtUpdates = (TextView) view.findViewById(R.id.txt_fragmentApps_update);

        txtFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                because we are using a fragment in another fragment, we must use getChildFragmentManager, but rel_main_parentAllView is in our,
//                activity so we must use AppCompatActivity convertion.

//                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentManager fragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.rel_main_parentAllView, new FragmentFavoritesApp());
                transaction.commit();
            }
        });

        txtUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPackageInfo();

//                for (int i = 0; i < installedPackages.size(); i++) {
//                    Log.i("LOG", "name :" + installedPackages.get(i).getPackageName() +
//                            " | version :" + installedPackages.get(i).getVersion());
//                }

                Gson gson = new GsonBuilder().create();
                JsonArray jsonArray = gson.toJsonTree(installedPackages).getAsJsonArray();
                ApiService service = ApiClient.getClient1().create(ApiService.class);
                Call<ResponseBody> call = service.sendInstallApp(jsonArray.toString());

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
//                            Toast.makeText(getContext(), response.body().string() + "", Toast.LENGTH_SHORT).show();
                            Log.i("LOG", "onResponse: " + response.body().string());
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
    }

    private void getPackageInfo() {

        PackageManager manager = getContext().getPackageManager();
        List<PackageInfo> installed = manager.getInstalledPackages(PackageManager.GET_META_DATA);

        for (PackageInfo packageInfo : installed) {

            InstalledPackage installedPackage = new InstalledPackage();

            installedPackage.setPackageName(packageInfo.packageName);
            installedPackage.setVersion(packageInfo.versionCode);

            installedPackages.add(installedPackage);
        }
    }
}
