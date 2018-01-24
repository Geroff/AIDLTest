// IBookManager.aidl
package com.example.lgf.aidl;

// Declare any non-default types here with import statements
import com.example.lgf.aidl.Book; // 1

interface IBookManager {
   void addBook(in Book book); // 2
   List<Book> getBookList();
}
