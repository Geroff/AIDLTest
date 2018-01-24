package com.example.lgf.aidl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent service = new Intent(this, BookManagerService.class);
        startService(service);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent service = new Intent(this, BookManagerService.class);
        stopService(service);
    }
}
