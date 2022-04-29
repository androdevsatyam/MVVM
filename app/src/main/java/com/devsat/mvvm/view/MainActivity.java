package com.devsat.mvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.devsat.mvvm.Adapter.DeviceAdapter;
import com.devsat.mvvm.R;
import com.devsat.mvvm.ViewModel.DeviceViewModel;
import com.devsat.mvvm.databinding.ActivityMainBinding;
import com.devsat.mvvm.model.DevicesModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DeviceViewModel viewModel;
    String tag = "MainActivity";
    ActivityMainBinding activityMainBinding;
    DeviceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(DeviceViewModel.class);

        viewModel.getDeviceList().observe(this, new Observer<List<DevicesModel>>() {
            @Override
            public void onChanged(List<DevicesModel> devicesModels) {
                Log.d(tag, "List Changed" + devicesModels);
                if (devicesModels!=null){
                    viewModel.setModel(devicesModels);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new Show(),"First")
                            .addToBackStack("First")
                            .commit();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new Show(),"First")
                .addToBackStack("First")
                .commit();
    }
}