package com.example.btproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btproject.databinding.ActivityScanDeviceBinding;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ScanDeviceActivity extends AppCompatActivity {
    ActivityScanDeviceBinding binding;
    ArrayList<BluetoothDevice> btDevices = new ArrayList<>();
    PairedDeviceAdapter pairedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scan_device);

        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
        if(pairedDevices.size() > 0){
            btDevices.addAll(0, pairedDevices);
        }

        pairedAdapter = new PairedDeviceAdapter(btDevices, this);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        binding.rlPaired.setLayoutManager(lm);
        binding.rlPaired.setAdapter(pairedAdapter);
        binding.rlPaired.setItemAnimator(new DefaultItemAnimator());
        pairedAdapter.notifyDataSetChanged();

        binding.btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
