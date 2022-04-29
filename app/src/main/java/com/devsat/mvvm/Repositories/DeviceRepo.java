package com.devsat.mvvm.Repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.devsat.mvvm.Global.Global;
import com.devsat.mvvm.apicall.Connection;
import com.devsat.mvvm.apicall.ConnectionInterface;
import com.devsat.mvvm.model.DevicesModel;
import com.devsat.mvvm.model.GetDeviceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceRepo {
    ConnectionInterface reference;
    String tag = "DeviceRepo";

    public DeviceRepo() {
        reference = Global.getApiCon();

    }

    public MutableLiveData<List<DevicesModel>> getMutableList() {
        MutableLiveData<List<DevicesModel>> devicemutable = new MutableLiveData<>();

        reference.getList().enqueue(new Callback<GetDeviceResponse>() {
            @Override
            public void onResponse(Call<GetDeviceResponse> call, Response<GetDeviceResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(tag, "Call Success");
                    devicemutable.setValue(response.body().getDevices());
                } else {
                    Log.d(tag, "Call data null");
                    devicemutable.setValue(null);
                }

            }

            @Override
            public void onFailure(Call<GetDeviceResponse> call, Throwable t) {
                Log.d(tag, "Call Failure=" + t.getMessage());
                devicemutable.setValue(null);
            }
        });
        return devicemutable;
    }
}
