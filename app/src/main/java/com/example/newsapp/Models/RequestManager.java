package com.example.newsapp.Models;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {

    Context context;
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://newsapi.org/v2/").addConverterFactory(GsonConverterFactory.create()).build();

    //method to manage our API call

    

    public RequestManager(Context context){
        this.context = context;
    }

    public interface CallNewsApi{
        //we are using get method
        @GET("top-headlines")
         Call<NewsApiResponse> callHeadlines(
                 @Query("country") String country,
                 @Query("category") String category,
                 @Query("q")String query,
                 @Query("apikey") String api_key
         );
    }
}
