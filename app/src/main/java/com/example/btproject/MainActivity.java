package com.example.btproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.btproject.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityMainBinding binding;
    BluetoothAdapter btAdapter;
    static final int REQUEST_ENABLE_BT = 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        binding.btnCommand.setOnClickListener(this);
        binding.btnBlueetooth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_command:
                //Check if bluetooth is enabled, if not then prompt user to enable bluetooth
                if(btAdapter == null){
                    Snackbar.make(binding.mainCl, R.string.device_no_bluetooth, Snackbar.LENGTH_SHORT).show();
                }else{
                    if(!btAdapter.isEnabled()){
                        Intent enableBt = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBt, REQUEST_ENABLE_BT);
                    }
                }

                break;

            case R.id.btn_blueetooth:
                if(btAdapter == null){
                    Snackbar.make(binding.mainCl, R.string.no_bluetooth, Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void toggleBluetooth(){ //on and off bluetooth

    }
}
