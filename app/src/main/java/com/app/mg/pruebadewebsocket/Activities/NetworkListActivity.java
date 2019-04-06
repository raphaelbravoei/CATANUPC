package com.app.mg.pruebadewebsocket.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.app.mg.pruebadewebsocket.Adapter.NetworkAdapter;
import com.app.mg.pruebadewebsocket.R;

import java.util.ArrayList;
import java.util.List;

public class NetworkListActivity extends AppCompatActivity {
    List<String> networkList = new ArrayList<>();

    //private RecyclerView networkRecyclerView;
    private NetworkAdapter adapter;
    //private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_list);
        networkList = getIntent().getStringArrayListExtra("networkList");
        //networkRecyclerView = findViewById(R.id.recycler_network);
        adapter = new NetworkAdapter(networkList);
        //layoutManager = new GridLayoutManager(this,1);
        //networkRecyclerView.setAdapter(adapter);
        //networkRecyclerView.setLayoutManager(layoutManager);
    }
}
