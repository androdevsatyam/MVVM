package com.devsat.mvvm.apicall;

import com.devsat.mvvm.model.DevicesModel;
import com.devsat.mvvm.model.GetDeviceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ConnectionInterface {

@GET("items.test")
    Call<GetDeviceResponse> getList();
}
