package com.example.lgf.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BookManagerService extends Service {
    private List<Book> bookList = new ArrayList<>();
    private Binder binder = new IBookManager.Stub() {
        @Override
        public void addBook(Book book) throws RemoteException {
            Log.d("BookManagerService", "add book:index-->" + book.index + ", name-->" + book.name);
            bookList.add(book);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            Log.d("BookManagerService", "book list-->" + bookList.size());
            return bookList;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        bookList.add(new Book(1, "JAVA"));
        bookList.add(new Book(2, "Android"));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
