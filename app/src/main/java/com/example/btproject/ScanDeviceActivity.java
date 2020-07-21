package com.example.btproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.btproject.databinding.ActivityScanDeviceBinding;

import android.os.Bundle;

public class ScanDeviceActivity extends AppCompatActivity {
    ActivityScanDeviceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scan_device);


    }
}
