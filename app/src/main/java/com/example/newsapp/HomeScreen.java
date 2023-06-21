package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.newsapp.Models.NewsApiResponse;
import com.example.newsapp.Models.NewsHeadLines;

import java.util.List;


public class HomeScreen extends AppCompatActivity implements SelectListener, View.OnClickListener{

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;

    Button b1,b2,b3,b4,b5,b6,b7;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // main activity for our application

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, "general", null);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching news articles");
        dialog.show();

        //button handling
        b1 = findViewById(R.id.btn_1);
        b1.setOnClickListener(this);
        b2 = findViewById(R.id.btn_2);
        b2.setOnClickListener(this);
        b3 = findViewById(R.id.btn_3);
        b3.setOnClickListener(this);
        b4 = findViewById(R.id.btn_4);
        b4.setOnClickListener(this);
        b5 = findViewById(R.id.btn_5);
        b5.setOnClickListener(this);
        b6 = findViewById(R.id.btn_6);
        b6.setOnClickListener(this);
        b7 = findViewById(R.id.btn_7);
        b7.setOnClickListener(this);

        searchView = findViewById(R.id.search_view);
        // Query text listener for the search view
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching News articles of "+query);
                dialog.show();

                RequestManager manager = new RequestManager(HomeScreen.this);
                manager.getNewsHeadlines(listener, "general", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }
       private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
           @Override
           public void onFetchData(List<NewsHeadLines> list, String message) {
               if (list.isEmpty()) {
                   Toast.makeText(HomeScreen.this, "No Data Found", Toast.LENGTH_SHORT).show();
                   dialog.dismiss();
               } else {
                   showNews(list);
                   dialog.dismiss();
               }
           }

           @Override
           public void onError(String message) {
               Toast.makeText(HomeScreen.this, "An Error Occurred From the Api", Toast.LENGTH_SHORT).show();

           }
       };

        private void showNews (List<NewsHeadLines> list) {

            recyclerView = findViewById(R.id.recyclerHome);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            adapter = new CustomAdapter(this, list, this);
            recyclerView.setAdapter(adapter);

        }

        //when clicked on the news article
    @Override
    public void OnNewsClicked(NewsHeadLines headLines) {
        startActivity(new Intent(HomeScreen.this, DetailsActivity.class)
                .putExtra("data", headLines));
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String category = button.getText().toString();
        //for dialog pop up
        dialog.setTitle("Fetching news articles of "+category);
        dialog.show();
        //to request the particular category asked from the user
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, category, null);
    }
}
