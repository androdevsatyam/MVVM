package com.devsat.mvvm.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.devsat.mvvm.Repositories.DeviceRepo;
import com.devsat.mvvm.model.DevicesModel;

import java.util.List;

public class DeviceViewModel extends ViewModel {
    DeviceRepo repo;

    MutableLiveData<DevicesModel> model = new MutableLiveData<>();
    MutableLiveData<List<DevicesModel>> downloaded = new MutableLiveData<>();

    public DeviceViewModel() {
        this.repo = new DeviceRepo();
    }

    public MutableLiveData<List<DevicesModel>> getDeviceList() {
        return repo.getMutableList();
    }

    public void setModel(List<DevicesModel> model) {
        this.downloaded.setValue(model);
    }

    public MutableLiveData<List<DevicesModel>> getModel() {
        return downloaded;
    }

    public void selectData(DevicesModel model) {
        this.model.setValue(model);
    }

    public MutableLiveData<DevicesModel> getSelectData() {
        return model;
    }

    public void updateList(DevicesModel devicesModel) {
        List<DevicesModel> models = downloaded.getValue();
        int pos = models.indexOf(devicesModel);
        models.set(pos,devicesModel);
        downloaded.setValue(models);
    }
}
