package com.example.gotdemo.screens.quotes;

import com.example.gotdemo.DemoApplicationComponent;
import com.example.gotdemo.dagger.scopes.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = QuotesActivityModule.class, dependencies = DemoApplicationComponent.class)
public interface QuotesActivityComponent {
    void injectQuotesActivity(QuotesActivity activity);
}
