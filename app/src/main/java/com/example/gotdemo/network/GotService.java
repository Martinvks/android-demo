package com.example.gotdemo.network;

import com.example.gotdemo.model.Quote;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface GotService {
    @GET("/quotes")
    Observable<Quote> getRandomQuote();
}
