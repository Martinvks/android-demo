package com.example.gotdemo.screens.quotes;

import android.support.annotation.NonNull;

import com.example.gotdemo.network.GotService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class QuotesActivityPresenter {
    private final GotService gotService;
    private final CompositeDisposable disposable = new CompositeDisposable();

    private QuotesActivityView quotesActivityView;

    public QuotesActivityPresenter(@NonNull GotService gotService) {
        this.gotService = gotService;
    }

    public void setQuotesActivityView(QuotesActivityView view) {
        quotesActivityView = view;
    }

    public void detatch() {
        disposable.clear();
        quotesActivityView = null;
    }

    public void onQuotesRefresh() {
        disposable.add(
                gotService.getRandomQuote()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(quote -> {
                                    if (quotesActivityView != null) {
                                        quotesActivityView.showQuote(quote);
                                    }
                                },
                                throwable -> {
                                    if (quotesActivityView != null) {
                                        quotesActivityView.showErrorMessage("Ooops");
                                    }
                                })
        );
    }

}
