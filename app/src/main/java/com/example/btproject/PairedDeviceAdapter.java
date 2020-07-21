package com.example.btproject;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.btproject.databinding.BluetoothDeviceLayoutBinding;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PairedDeviceAdapter extends RecyclerView.Adapter<PairedDeviceAdapter.AdapterViewholder>{

    private ArrayList<BluetoothDevice> btDevices;
    private Context mCtx;
    public PairedDeviceAdapter(ArrayList<BluetoothDevice> dev, Context c){
        this.btDevices = dev;
        this.mCtx = c;
    }

    @NonNull
    @Override
    public AdapterViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        BluetoothDeviceLayoutBinding b = DataBindingUtil.inflate(inflater, R.layout.bluetooth_device_layout, parent, false);
        return new AdapterViewholder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewholder holder, int position) {
        BluetoothDevice device = btDevices.get(position);
        holder.binding.txtDeviceName.setText(device.getName());
        holder.binding.txtDeviceMac.setText(device.getAddress());

        holder.binding.radioDevice.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return btDevices.size();
    }

    public class AdapterViewholder extends RecyclerView.ViewHolder{
        BluetoothDeviceLayoutBinding binding;
        public AdapterViewholder(@NonNull BluetoothDeviceLayoutBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

}
