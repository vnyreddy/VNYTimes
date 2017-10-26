package com.vinay.wizdem.vnytimes.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vinay.wizdem.vnytimes.R;
import com.vinay.wizdem.vnytimes.adapters.BooksListAdapter;
import com.vinay.wizdem.vnytimes.models.books.BooksList;
import com.vinay.wizdem.vnytimes.models.books.BooksListResult;
import com.vinay.wizdem.vnytimes.rest.ApiClient;
import com.vinay.wizdem.vnytimes.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BooksListFragment extends Fragment {
    private final static String API_KEY = "438eee87377348388ac618130a22f118";
    private final static String LIST = "hardcover-fiction";
    private RecyclerView mRecyclerView;
    private View rootView;

    public BooksListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_books_list, container, false);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.books_list_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<BooksList> call = apiInterface.getBooksList(API_KEY,LIST);
        call.enqueue(new Callback<BooksList>() {
            @Override
            public void onResponse(Call<BooksList> call, Response<BooksList> response) {
                List<BooksListResult> booksListResults = response.body().getBooksListResults();

                mRecyclerView.setAdapter(new BooksListAdapter(booksListResults,rootView.getContext()));

                Toast.makeText(getActivity(),"request sucess!!"+booksListResults.size(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<BooksList> call, Throwable t) {
                Toast.makeText(getActivity(),"request failed!!"+t,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
