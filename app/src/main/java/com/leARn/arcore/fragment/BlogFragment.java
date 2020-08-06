package com.leARn.arcore.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leARn.arcore.Adapters.NewsViewAdapter;
import com.leARn.arcore.Blog.ApiClient;
import com.leARn.arcore.Blog.RetrofitApi;
import com.leARn.arcore.R;
import com.leARn.arcore.bean.Article;
import com.leARn.arcore.bean.News;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BlogFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Article> data;
    NewsViewAdapter adapter;
    final String BLOG_API_KEY = "7457f8ceb29c4d79acdc9e14c26a3a13";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blog , container ,false);
        recyclerView = v.findViewById(R.id.recyclerView);

        data = new ArrayList<>();
        LoadJSON();
        return v;
    }

    private void LoadJSON() {
        Log.e("JSON" , "inside LOADJSON");
        RetrofitApi client = ApiClient.getApiClient().create(RetrofitApi.class);
        Call<News> call;
        call = client.getNews("kids Science" , BLOG_API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getArticles()!=null){
                    if (!data.isEmpty()){
                        data.clear();
                    }
                    data = response.body().getArticles();
                    adapter = new NewsViewAdapter(getContext() , data);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage()+ "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
