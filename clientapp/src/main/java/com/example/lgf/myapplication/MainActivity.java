package com.example.lgf.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.lgf.aidl.Book;
import com.example.lgf.aidl.IBookManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private IBookManager iBookManager;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBookManager = IBookManager.Stub.asInterface(service);
            try {
                iBookManager.addBook(new Book(3, "Python"));
                List<Book> bookList = iBookManager.getBookList();
                Log.d("MainActivity", "book size-->" + bookList.size());
                System.out.println("book size-->" + bookList.size());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(Intent.ACTION_MAIN); // 1
        ComponentName componentName = new ComponentName("com.example.lgf.aidl", "com.example.lgf.aidl.BookManagerService"); // 2
        intent.setComponent(componentName); // 3
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
