package com.example.app2;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app2.Alibaba.FragmentAlibaba.FragmentBusInfo;
import com.example.app2.Alibaba.FragmentAlibaba.FragmentChair;
import com.example.app2.Alibaba.FragmentAlibaba.PassengersFragment;
import com.example.app2.Alibaba.Model.AlibabaChairModel;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

public class ChairSelectionActivity extends AppCompatActivity {

    StepView stepView;
    ImageView imgBack;
    FrameLayout frameLayout;

    ArrayList<AlibabaChairModel> chairModels;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chair_selection);

        setupViews();

        stepView.getState()
                .steps(new ArrayList<String>() {{
                    add("انتخاب صندلی");
                    add("افزودن مسافر");
                    add("تایید اطلاعات بلیط");
                    add("پرداخت");
                }})

                .stepsNumber(4)
                .animationDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime))
                .stepLineWidth(getResources().getDimensionPixelSize(R.dimen.dp1))
                .textSize(getResources().getDimensionPixelSize(R.dimen.sp14))
                .stepNumberTextSize(getResources().getDimensionPixelSize(R.dimen.sp16))
                .typeface(ResourcesCompat.getFont(ChairSelectionActivity.this, R.font.font))
                .commit();

        stepView.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {
                int newStep = step - 1;
                stepView.go(newStep, true);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupViews() {

        fragmentManager = getSupportFragmentManager();
        chairModels = getIntent().getParcelableArrayListExtra("chairs");
        String price = getIntent().getExtras().getString("price");

        Bundle bundle = new Bundle();
        FragmentChair fragmentChair = new FragmentChair();
        bundle.putParcelableArrayList("chairs", chairModels);
        bundle.putString("price", price);
        fragmentChair.setArguments(bundle);

        frameLayout = (FrameLayout) findViewById(R.id.frm_chairSelection_fragments);
        imgBack = (ImageView) findViewById(R.id.img_chairSelection_back);
        stepView = (StepView) findViewById(R.id.sv_chairSelection_stepView);

        FragmentManager manager = fragmentManager;
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frm_chairSelection_fragments, fragmentChair);
//        transaction.addToBackStack(null);
        transaction.commit();

        fragmentChair.setOnSubmitClicked(new FragmentChair.OnSubmitClicked() {
            @Override
            public void onClicked(String number, String price) {

                Bundle bndl = new Bundle();
                Toast.makeText(ChairSelectionActivity.this, number, Toast.LENGTH_SHORT).show();
                FragmentTransaction addTransaction = fragmentManager.beginTransaction();
                PassengersFragment passengersFragment = new PassengersFragment();
                bndl.putString("number", number);
                bndl.putString("price", price);
                passengersFragment.setArguments(bndl);

                passengersFragment.setOnPassengerNameReceive(new PassengersFragment.OnPassengerNameReceive() {
                    @Override
                    public void onNameReceive(String name) {

                        stepView.go(2, true);
                        FragmentBusInfo fragmentBusInfo = new FragmentBusInfo();
                        FragmentTransaction infoTrans = fragmentManager.beginTransaction();
                        infoTrans.add(R.id.frm_chairSelection_fragments, fragmentBusInfo);
                        infoTrans.addToBackStack(null);
                        infoTrans.commit();
                    }
                });

                addTransaction.add(R.id.frm_chairSelection_fragments, passengersFragment);
                addTransaction.addToBackStack(null);
                addTransaction.commit();
                stepView.go(1, true);


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Fragment fragment = fragmentManager.findFragmentById(R.id.frm_chairSelection_fragments);

        if (fragment instanceof FragmentChair) {
            Toast.makeText(this, "chair fragment", Toast.LENGTH_SHORT).show();
        } else if (fragment instanceof PassengersFragment) {
            Toast.makeText(this, "passenger fragment", Toast.LENGTH_SHORT).show();
            stepView.go(0, true);
        } else if (fragment instanceof FragmentBusInfo) {
            Toast.makeText(this, "bus info fragment", Toast.LENGTH_SHORT).show();
            stepView.go(1, true);
        }
    }
}
