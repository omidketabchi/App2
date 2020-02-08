package com.example.app2.SampleProject.NotificationSample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.R;

import java.util.List;

public class ListFragment extends Fragment implements View.OnClickListener {
    private List<City> mCities;

    private AppNotificationManager mNotificationManager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            mNotificationManager = new AppNotificationManager(getActivity());
        }
    }

    @Override
    public View onCreateView(final @NonNull LayoutInflater inflater,
                             final @Nullable ViewGroup container,
                             final @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mCities = DummyData.getDummyData();

        rootView.findViewById(R.id.send_all_notifications).setOnClickListener(this);

        RecyclerView mRecyclerView = rootView.findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(mRecyclerView.getContext(), LinearLayoutManager.VERTICAL));
        CitiesAdapter mAdapter = new CitiesAdapter(mCities, new OnItemClickListener() {
            @Override
            public void onNotificationClick(int position) {
                mNotificationManager.showDetailsNotificationWithAllCitiesAction(mCities.get(position));
            }

            @Override
            public void onContentClick(int position) {
                Navigator.navigateToDetails(getActivity(), mCities.get(position).getId());
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_all_notifications:
                for (City city : mCities) {
                    mNotificationManager.showDetailsNotificationWithAllCitiesAction(city);
                }
                mNotificationManager.showBundleNotification(mCities.size());
                break;
        }
    }
}