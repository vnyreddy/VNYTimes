package com.vinay.wizdem.vnytimes.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vinay.wizdem.vnytimes.R;
import com.vinay.wizdem.vnytimes.adapters.ArticleListAdapter;
import com.vinay.wizdem.vnytimes.models.articles.Articles;
import com.vinay.wizdem.vnytimes.models.articles.Result;
import com.vinay.wizdem.vnytimes.rest.ApiClient;
import com.vinay.wizdem.vnytimes.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleListFragment extends Fragment {
    private final static String API_KEY = "438eee87377348388ac618130a22f118";
    private RecyclerView mRecyclerView;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_article_list, container, false);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.article_list_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Articles> call = apiInterface.getArticleList(API_KEY);
        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                List<Result> results = response.body().getResults();

                mRecyclerView.setAdapter(new ArticleListAdapter(results,rootView.getContext()));

                Toast.makeText(getActivity(),"request sucess!!"+results.size(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {
                Toast.makeText(getActivity(),"request failed!!"+t,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
