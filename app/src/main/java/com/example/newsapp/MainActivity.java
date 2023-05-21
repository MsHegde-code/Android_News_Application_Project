package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button NextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NextBtn = (Button) findViewById(R.id.btnNext);
        final Loading_dialog loading_dialog = new Loading_dialog(MainActivity.this);
        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading_dialog.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading_dialog.dismissDialog();
                    }
                },6000);
                openHomeScreen();
            }
        });



    }
    public void openHomeScreen(){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}