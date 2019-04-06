package com.app.mg.pruebadewebsocket.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.mg.connectionlibraryandroid.Implementations.ConnectMethods;
import com.app.mg.pruebadewebsocket.R;

import java.util.ArrayList;
import java.util.List;


public class ControlActivity extends AppCompatActivity {

    String myIpAddress;
    String port = "8080";
    List<String> ipAvaliables = new ArrayList<>();
    ConnectMethods connectMethods = new ConnectMethods();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        myIpAddress = connectMethods.FindMyIpAddress(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                findServers();
            }
        }).start();
    }

    private void CambiaActivity(){
        Intent intent = new Intent(ControlActivity.this,NetworkListActivity.class);
        intent.putStringArrayListExtra("networkList", (ArrayList<String>) ipAvaliables);
        startActivity(intent);

    }
    private void findServers() {
        ipAvaliables = connectMethods.FindServers(myIpAddress,port);
        CambiaActivity();
    }
}
