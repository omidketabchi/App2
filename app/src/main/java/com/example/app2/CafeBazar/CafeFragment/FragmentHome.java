package com.example.app2.CafeBazar.CafeFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.app2.CafeBazar.CafeAdapter.BannersAdapter;
import com.example.app2.CafeBazar.CafeAdapter.NewAppAdapter;
import com.example.app2.CafeBazar.CafeModel.App;
import com.example.app2.CafeBazar.CafeModel.Banner;
import com.example.app2.CafeBazar.CafeModel.Slider;
import com.example.app2.CafeBazar.Retrofit.ApiClient;
import com.example.app2.CafeBazar.Retrofit.ApiService;
import com.example.app2.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment implements BaseSliderView.OnSliderClickListener {

    SliderLayout sliderLayout;
    RecyclerView bannerRecycler;
    RecyclerView appsRecycler;
    RecyclerView updatedAppRecycler;

    ArrayList<String> sliderArray;
    ArrayList<String> bannersArray;

    List<Slider> sliders;
    List<Banner> banners;
    List<App> newApps;
    List<App> updatedApps;
    ImageView imgProfile;

    String phoneNumber = "";
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cafe_home,
                container, false);

        setupViews(view);

        setSlideForSlider();

        getBanners();

        getApps();

        getNewUpdatedApps();

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getContext().getSharedPreferences("home", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("userName", "");

                if (username.equals("")) {
                    BottomSheetDialog bottomSheetDialog =
                            new BottomSheetDialog(getActivity());

                    View bottomSheetView = getLayoutInflater().
                            inflate(R.layout.cafe_buttom_sheet_dialog, null);

                    Button btnRegister = (Button) bottomSheetView.
                            findViewById(R.id.btn_buttomSheet_register);

                    EditText edtPhoneNumber = (EditText) bottomSheetView.
                            findViewById(R.id.edt_buttomSheet_phoneNumber);

                    ProgressBar progressBar = (ProgressBar) bottomSheetView.findViewById(R.id.pb_bottomSheet_progressBar);

                    bottomSheetDialog.setContentView(bottomSheetView);

                    BottomSheetBehavior bottomSheetBehavior =
                            BottomSheetBehavior.from((View) bottomSheetView.getParent());

                    bottomSheetBehavior.setPeekHeight(
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400,
                                    getResources().getDisplayMetrics())
                    );

                    btnRegister.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ApiService service = ApiClient.getClient2().
                                    create(ApiService.class);
                            phoneNumber = edtPhoneNumber.getText().toString();
                            if (phoneNumber.equals("")) {
                                edtPhoneNumber.setError("لطفا شماره همراه خود را وارد کنید");
                            } else {

                                progressBar.setVisibility(View.VISIBLE);
                                Call<ResponseBody> call = service.sendNumber(phoneNumber);
                                call.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        try {
                                            Toast.makeText(getContext(), response.body().string(), Toast.LENGTH_SHORT).show();
                                            bottomSheetDialog.dismiss();
                                            BottomSheetDialog validationDialog = new BottomSheetDialog(getContext());
                                            View validationView = getLayoutInflater().inflate(R.layout.bottom_sheet_validation_code, null);

                                            validationDialog.setContentView(validationView);
                                            validationDialog.show();
                                            EditText edtValidationCode = (EditText) validationView.findViewById(R.id.edt_buttomSheetValidation_code);
                                            Button btnValidation = (Button) validationView.findViewById(R.id.btn_buttomSheetValidation_validation);

                                            btnValidation.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    if (edtPhoneNumber.getText().toString().equals("")) {
                                                        edtPhoneNumber.setError("کد فعالسازی را وارد کنید.");
                                                    } else {
                                                        ApiService service3 = ApiClient.getClient2().create(ApiService.class);
                                                        Call<ResponseBody> call1 = service3.sendValidationCode(edtValidationCode.getText().toString(),
                                                                phoneNumber);

                                                        call1.enqueue(new Callback<ResponseBody>() {
                                                            @Override
                                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                                try {
                                                                    Log.i("LOG", "onResponse: " + response.body().string());
                                                                    String userId = response.body().string().trim();
                                                                    if (userId.equals("ok")) {
                                                                        Toast.makeText(getContext(), "به بازار خوش آمدید", Toast.LENGTH_SHORT).show();

                                                                        validationDialog.dismiss();
                                                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                                                        editor.putString("userName", phoneNumber);
                                                                        editor.putString("userId", userId);
                                                                        editor.apply();
                                                                    } else {
                                                                        Toast.makeText(getContext(), "کد فعالسازی اشتباه است", Toast.LENGTH_SHORT).show();
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
                                                }
                                            });
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                                    }
                                });
                            }

                        }
                    });

                    bottomSheetDialog.show();
                } else {
                    Bundle bundle = new Bundle();

                    bundle.putString("userName", username);
                    FragmentManager manager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    FragmentAccount fragmentAccount = new FragmentAccount();
                    fragmentAccount.setArguments(bundle);
                    transaction.replace(R.id.rel_main_parentAllView, fragmentAccount);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });


        return view;
    }

    private void setupViews(View view) {

        sliderArray = new ArrayList<>();
        bannersArray = new ArrayList<>();
        newApps = new ArrayList<>();
        updatedApps = new ArrayList<>();

        imgProfile = (ImageView) view.findViewById(R.id.img_cb_main_profile);
        sliderLayout = (SliderLayout) view.findViewById(R.id.slider);
        bannerRecycler = (RecyclerView) view.findViewById(R.id.rv_fragmentHome_banners);
        appsRecycler = (RecyclerView) view.findViewById(R.id.rv_fragmentHome_newApp);
        updatedAppRecycler = (RecyclerView) view.findViewById(R.id.rv_fragmentHome_updatedApp);

        bannerRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        appsRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        updatedAppRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getContext(), "test", Toast.LENGTH_SHORT).show();
    }

    public void setSlideForSlider() {

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<List<Slider>> call = service.getSliders();

        call.enqueue(new Callback<List<Slider>>() {
            @Override
            public void onResponse(Call<List<Slider>> call, Response<List<Slider>> response) {
                sliders = response.body();

                for (int i = 0; i < sliders.size(); i++) {
                    sliderArray.add(sliders.get(i).getUrl());
                }

                for (int i = 0; i < sliderArray.size(); i++) {

                    DefaultSliderView defaultSliderView = new DefaultSliderView(getContext());

                    defaultSliderView.image(sliderArray.get(i))
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                            .setOnSliderClickListener(FragmentHome.this);

                    defaultSliderView.bundle(new Bundle());
                    sliderLayout.addSlider(defaultSliderView);
                }
            }

            @Override
            public void onFailure(Call<List<Slider>> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getBanners() {

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<List<Banner>> call = service.getBanners();

        call.enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {
                banners = response.body();

                bannerRecycler.setAdapter(new BannersAdapter(banners, getContext()));
            }

            @Override
            public void onFailure(Call<List<Banner>> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getApps() {

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<App>> call = apiService.getNewApps();

        call.enqueue(new Callback<List<App>>() {
            @Override
            public void onResponse(Call<List<App>> call, Response<List<App>> response) {

                newApps = response.body();

                appsRecycler.setAdapter(new NewAppAdapter(newApps, getActivity()));
            }

            @Override
            public void onFailure(Call<List<App>> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getNewUpdatedApps() {

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<App>> call = apiService.getUpdatedApps();

        call.enqueue(new Callback<List<App>>() {
            @Override
            public void onResponse(Call<List<App>> call, Response<List<App>> response) {

                updatedApps = response.body();
//              57-58-59
                updatedAppRecycler.setAdapter(new NewAppAdapter(updatedApps, getActivity()));
            }

            @Override
            public void onFailure(Call<List<App>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
