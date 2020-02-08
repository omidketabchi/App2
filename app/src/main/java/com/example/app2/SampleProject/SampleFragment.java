package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.app2.SampleProject.Fragments.TestFragment;
import com.example.app2.R;

public class SampleFragment extends AppCompatActivity {

    Button btnAdd;
    FrameLayout fragmentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_fragment);

        btnAdd = (Button)findViewById(R.id.btn_sampleFragment_addFragment);
        fragmentFrame = (FrameLayout)findViewById(R.id.frame_sampleFragment);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.frame_sampleFragment, new TestFragment());
                transaction.addToBackStack(null);// har fragmenti ke ba code bala add mishavad, dar stack negahdari mikonad.
                                                // va ba back zadan in fragment ha az stack biroon miayad.
                transaction.commit();
            }
        });
    }
}
