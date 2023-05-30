package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.newsapp.Models.NewsApiResponse;
import com.example.newsapp.Models.NewsHeadLines;

import java.util.List;


public class HomeScreen extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // main activity for our application

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, "general", null);

    }
       private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
           @Override
           public void onFetchData(List<NewsHeadLines> list, String message) {
               showNews(list);
           }

           @Override
           public void onError(String message) {

           }
       };

        private void showNews (List<NewsHeadLines> list) {

            recyclerView = findViewById(R.id.recyclerHome);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            adapter = new CustomAdapter(this, list);
            recyclerView.setAdapter(adapter);

        }
    }
