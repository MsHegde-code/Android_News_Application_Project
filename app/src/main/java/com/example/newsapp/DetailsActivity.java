package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.Models.NewsHeadLines;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    //java class to get the details for the news articles when clicked
    NewsHeadLines headLines;
    TextView txt_Title, txt_Author, txt_time, txt_detail, txt_content;
    ImageView imgNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        txt_Title = findViewById(R.id.text_detail_title);
        txt_Author = findViewById(R.id.text_detail_author);
        txt_detail = findViewById(R.id.text_detail_detail);
        txt_content = findViewById(R.id.text_detail_content);
        txt_time = findViewById(R.id.text_detail_time);
        imgNews = findViewById(R.id.img_detail_news);


        headLines = (NewsHeadLines) getIntent().getSerializableExtra("data");

        //retrieving the content from the News Api methods to feed into the particular news category card


        txt_Title.setText(headLines.getTitle());
        txt_Author.setText(headLines.getAuthor());
        txt_detail.setText(headLines.getDescription());
        txt_time.setText(headLines.getPublishedAt());
        txt_content.setText(headLines.getContent());
        Picasso.get().load(headLines.getUrlToImage()).into(imgNews);

    }
}