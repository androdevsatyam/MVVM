package com.devsat.mvvm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.devsat.mvvm.R;
import com.devsat.mvvm.ViewModel.DeviceViewModel;
import com.devsat.mvvm.databinding.ActivityViewBinding;
import com.devsat.mvvm.model.DevicesModel;

public class ViewActivity extends Fragment {

    DeviceViewModel viewModel;
    ActivityViewBinding binding;
    DevicesModel devicesModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_view, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(requireActivity()).get(DeviceViewModel.class);

        viewModel.getSelectData().observe(getViewLifecycleOwner(), new Observer<DevicesModel>() {
            @Override
            public void onChanged(DevicesModel model) {
                devicesModel=model;
                if (model.getPlatform().equalsIgnoreCase("Sercomm G450")) {
                    binding.image.setImageResource(R.drawable.vera_plus_big);
                } else if (model.getPlatform().equalsIgnoreCase("Sercomm G550")) {
                    binding.image.setImageResource(R.drawable.vera_secure_big);
                } else
                    binding.image.setImageResource(R.drawable.vera_edge_big);

                binding.title.setText(model.getPlatform());
                binding.sn.setText("SN NO. : "+model.getPK_Device());
                binding.mac.setText("Mac Address : "+model.getMacAddress());
                binding.firmware.setText("Firmware : "+model.getFirmware());
                binding.ip.setText("IP Address : "+model.getInternalIP());
            }
        });


        binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askUpdate();
            }
        });
    }

    private void askUpdate() {
        EditText editText=new EditText(getContext());
        editText.setText(devicesModel.getPlatform());
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Update");
        builder.setView(editText);

        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(editText.getText().toString().isEmpty())
                    editText.setError("Empty Not allowed");
                else{
                    devicesModel.setPlatform(editText.getText().toString());
                    viewModel.updateList(devicesModel);
                    viewModel.selectData(devicesModel);
                    Toast.makeText(getContext(),"Updated",Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog dialog=builder.create();
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "cancel", (dialog1, which) -> dialog1.dismiss());
        dialog.show();
    }
}
