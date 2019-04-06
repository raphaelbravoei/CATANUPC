package com.app.mg.pruebadewebsocket.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.app.mg.connectionlibraryandroid.Implementations.ConnectMethods;
import com.app.mg.connectionlibraryandroid.Implementations.MessageMethods;
import com.app.mg.pruebadewebsocket.Entities.MessageBody;
import com.app.mg.pruebadewebsocket.Entities.RepeatListener;
import com.app.mg.pruebadewebsocket.R;
import com.app.mg.pruebadewebsocket.WebSocket.WebsocketClient;
import com.app.mg.pruebadewebsocket.WebSocket.WebsocketServer;

import org.java_websocket.WebSocket;

import java.net.InetSocketAddress;

public class WSAndControlActivity extends AppCompatActivity {

    String port = "8080";
    String ipAddress;
    EditText etMessage;
    Button btnSendMessage;

    //ImageButton btnUp, btnLeft, btnDown, btnRight;

    ConnectMethods connectMethods = new ConnectMethods();
    MessageMethods<MessageBody, WebsocketClient,WebSocket> messageMethods = new MessageMethods<>();

    WebsocketClient wsClient;
    InetSocketAddress inetSockAddress;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wsand_control);

        ipAddress = connectMethods.FindMyIpAddress(this);

        etMessage = findViewById(R.id.et_message);
        btnSendMessage = findViewById(R.id.btn_send_message);


        //btnUp = findViewById(R.id.ib_up);
        //btnLeft = findViewById(R.id.ib_left);
        //btnDown = findViewById(R.id.ib_down);
        //btnRight = findViewById(R.id.ib_right);


        /*btnUp.setOnTouchListener(new RepeatListener(50, 50, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessageBody("UP");
            }
        }));

        btnLeft.setOnTouchListener(new RepeatListener(50, 50, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessageBody("LEFT");
            }
        }));

        btnDown.setOnTouchListener(new RepeatListener(50, 50, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessageBody("DOWN");
            }
        }));
        btnRight.setOnTouchListener(new RepeatListener(50, 50, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessageBody("RIGHT");
            }
        }));*/

       /* btnSendMessage.setOnClickListener(v -> {
            SendMessageBody(etMessage.getText().toString());
        });*/

        SetWServerAndStart();

        Handler handler = new Handler();
        //handler.postDelayed(this::connectWebSocket, 3500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wsClient != null) wsClient.close();
    }

    private void connectWebSocket() {
        wsClient = new WebsocketClient(connectMethods.GetUriServer(ipAddress, port));
        wsClient.connect();
    }

    private void SendMessageBody(String message) {
        MessageBody messageBody = new MessageBody()
                .setMessage(message)
                .setSender(ipAddress)
                .setToTV(true);

        messageMethods.SendMessageBody(messageBody, wsClient, ipAddress);
    }

    private void SetWServerAndStart() {
        inetSockAddress = connectMethods.GetISocketAddres(this, port);
        WebsocketServer wsServer = new WebsocketServer(inetSockAddress);
        wsServer.start();
    }
}