package com.example.gotdemo.screens.quotes;

import com.example.gotdemo.model.Quote;


public interface QuotesActivityView {

    void showQuote(Quote quote);

    void showErrorMessage(String message);
}
