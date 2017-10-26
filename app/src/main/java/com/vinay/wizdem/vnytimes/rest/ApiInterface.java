package com.vinay.wizdem.vnytimes.rest;

import com.vinay.wizdem.vnytimes.models.articles.Articles;
import com.vinay.wizdem.vnytimes.models.books.AuthorHistory;
import com.vinay.wizdem.vnytimes.models.books.BooksList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vinay_1 on 10/25/2017.
 */

public interface ApiInterface {
    @GET("topstories/v2/home.json")
    Call<Articles> getArticleList(@Query("api_key") String api_key);

    @GET("books/v2/lists.json")
    Call<BooksList> getBooksList(@Query("api-key") String api_key, @Query("list") String list);

    @GET("books/v3/lists/best-sellers/history.json")
    Call<AuthorHistory> getAuthorHistory(@Query("api-key") String api_key, @Query("author") String author);

}
