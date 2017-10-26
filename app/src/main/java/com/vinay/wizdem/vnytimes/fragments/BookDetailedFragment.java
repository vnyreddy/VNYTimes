package com.vinay.wizdem.vnytimes.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vinay.wizdem.vnytimes.R;
import com.vinay.wizdem.vnytimes.adapters.AuthorHistoryListAdapter;
import com.vinay.wizdem.vnytimes.models.books.AuthorHistory;
import com.vinay.wizdem.vnytimes.models.books.AuthorHistoryResult;
import com.vinay.wizdem.vnytimes.rest.ApiClient;
import com.vinay.wizdem.vnytimes.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookDetailedFragment extends Fragment {
    private final static String API_KEY = "438eee87377348388ac618130a22f118";
    private RecyclerView mRecyclerView;
    private View rootView;
    private String author;
    private String title;
    private TextView selected_title;
    private TextView selected_author;

    public BookDetailedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        title = getArguments().getString("title");
        author = getArguments().getString("author");
        rootView = inflater.inflate(R.layout.fragment_book_detailed, container, false);
        selected_title = (TextView)rootView.findViewById(R.id.book_title_history);
        selected_author = (TextView)rootView.findViewById(R.id.book_author_name);
        selected_title.setText(title);
        selected_author.setText(author);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.author_history_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<AuthorHistory> call = apiInterface.getAuthorHistory(API_KEY,author);
        call.enqueue(new Callback<AuthorHistory>() {
            @Override
            public void onResponse(Call<AuthorHistory> call, Response<AuthorHistory> response) {
                List<AuthorHistoryResult> authorHistoryResult = response.body().getAuthorHistoryResults();

                mRecyclerView.setAdapter(new AuthorHistoryListAdapter(authorHistoryResult,title,author,rootView.getContext()));
            }

            @Override
            public void onFailure(Call<AuthorHistory> call, Throwable t) {

            }
        });
    }
}
