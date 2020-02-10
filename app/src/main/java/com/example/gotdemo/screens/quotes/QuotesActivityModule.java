package com.example.gotdemo.screens.quotes;

import com.example.gotdemo.dagger.scopes.ActivityScope;
import com.example.gotdemo.network.GotService;

import dagger.Module;
import dagger.Provides;

@Module
public class QuotesActivityModule {

    @ActivityScope
    @Provides
    QuotesActivityPresenter quotesActivityPresenter(GotService gotService) {
        return new QuotesActivityPresenter(gotService);
    }
}
