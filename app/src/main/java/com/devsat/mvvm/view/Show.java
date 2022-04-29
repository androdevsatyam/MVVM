package com.devsat.mvvm.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devsat.mvvm.Adapter.DeviceAdapter;
import com.devsat.mvvm.R;
import com.devsat.mvvm.ViewModel.DeviceViewModel;
import com.devsat.mvvm.databinding.FragmentShowBinding;
import com.devsat.mvvm.model.DevicesModel;

import java.util.List;

public class Show extends Fragment {

    FragmentShowBinding binding;
    DeviceViewModel viewModel;
    DeviceAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(requireActivity()).get(DeviceViewModel.class);

        binding.list.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.list.setHasFixedSize(true);

        viewModel.getModel().observe(getViewLifecycleOwner(), new Observer<List<DevicesModel>>() {
            @Override
            public void onChanged(List<DevicesModel> devicesModels) {
                if (devicesModels != null) {
//                    viewModel.setModel(devicesModels);
                    adapter = new DeviceAdapter(getContext(), devicesModels);
                    binding.list.setAdapter(adapter);

                    adapter.setClickListener(new DeviceAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(View view, int postition) {
                            viewModel.selectData(adapter.getItemAt(postition));
                            getParentFragmentManager().beginTransaction().replace(R.id.fragment, new ViewActivity()).commit();
                        }
                    });
                }
            }
        });

    }
}