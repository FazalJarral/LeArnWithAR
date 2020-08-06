package com.leARn.arcore.Blog;

import com.leARn.arcore.bean.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {
    @GET("everything")
    Call<News> getNews(
            @Query("q")
                    String keyword ,
            @Query("apikey")
                    String api
    );

}