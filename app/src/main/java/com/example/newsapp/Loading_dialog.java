package com.example.newsapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import java.util.concurrent.TimeUnit;
public class Loading_dialog {

    private final Activity activity;
    private AlertDialog dialog;
    Loading_dialog(Activity myActivity){
        activity = myActivity;
    }
    void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog,null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }
    void dismissDialog(){
        //providing delay
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
            dialog.dismiss();

    }
}
