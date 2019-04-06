package com.app.mg.pruebadewebsocket.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.app.mg.pruebadewebsocket.Activities.ControlWsActivity;
import com.app.mg.pruebadewebsocket.R;

import java.util.List;

public class NetworkAdapter {
    List<String> networkList;

    public NetworkAdapter(List<String> networkList){
        this.networkList = networkList;
    }



    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.network_item, viewGroup, false));
    }


    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.updateViews(networkList.get(i));
    }


    public int getItemCount() {
        return networkList.size();
    }

    public class ViewHolder{
        TextView networkAddress;
        Button btnConnectWS;
        public ViewHolder(@NonNull View itemView) {
            //super(itemView);

            networkAddress = itemView.findViewById(R.id.network_ip);
            btnConnectWS = itemView.findViewById(R.id.btn_connect_ws);
            /*btnConnectWS.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(),ControlWsActivity.class);
                intent.putExtra("networkIp",networkAddress.getText().toString());
                v.getContext().startActivity(intent);
            });*/
        }
        public void updateViews(String networkIp) {
            networkAddress.setText(networkIp);
        }

    }
}
