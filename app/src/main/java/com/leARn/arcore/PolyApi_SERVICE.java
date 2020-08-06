package com.leARn.arcore;

import com.leARn.arcore.bean.ModelList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface PolyApi_SERVICE {
    @Headers("Content-Type:model/gltf+json")
 //   @Headers({"Accept: application/json"})
    @GET("assets")
    Call<ModelList> getModel(
            @Query("keywords") String keyword,
            @Query("format") String format,
            @Query("key") String api_key

    );
}
