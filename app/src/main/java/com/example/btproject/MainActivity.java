package com.example.btproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

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
        binding.btSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    updateViewOnBtEnabled();
                }else{
                    updateViewOnBtDisabled();
                }
            }
        });


        if(btAdapter.isEnabled()){
            updateViewOnBtEnabled();

        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_command:
                //Check if bluetooth turned on, if not, then show message to turn on bluetooth
                if(!btAdapter.isEnabled()){
                    Snackbar.make(binding.mainCl, R.string.bt_is_off, Snackbar.LENGTH_SHORT).setAction(R.string.turn_on, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            turnBluetoothOn();
                        }
                    }).show();
                }
                break;
        }
    }

    private void turnBluetoothOn(){
        Intent btIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(btIntent, REQUEST_ENABLE_BT);
    }

    private void updateViewOnBtEnabled(){
        binding.txtConnectionStatus.setText(R.string.bt_connected);
        binding.btDot.setImageResource(R.drawable.green_dot);
        binding.txtDeviceName.setText(btAdapter.getName());
        binding.txtOff.setTextColor(getResources().getColor(R.color.txt_inactive));
        binding.txtOn.setTextColor(getResources().getColor(R.color.text_on));
        if(!binding.btSwitch.isChecked()){
            binding.btSwitch.setChecked(true);
        }
    }

    private void updateViewOnBtDisabled(){
        binding.txtConnectionStatus.setText(R.string.bt_disconnected);
        binding.btDot.setImageResource(R.drawable.red_dot);
        binding.txtDeviceName.setText(R.string.no_device);
        binding.txtOff.setTextColor(getResources().getColor(R.color.text_off));
        binding.txtOn.setTextColor(getResources().getColor(R.color.txt_inactive));
        if(binding.btSwitch.isChecked()){
            binding.btSwitch.setChecked(false);
        }
    }


}
