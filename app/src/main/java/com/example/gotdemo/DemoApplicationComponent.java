package com.example.gotdemo;

import com.example.gotdemo.dagger.modules.GotServiceModule;
import com.example.gotdemo.dagger.scopes.DemoApplicationScope;
import com.example.gotdemo.network.GotService;

import dagger.Component;

@DemoApplicationScope
@Component(modules = {GotServiceModule.class})
public interface DemoApplicationComponent {
    GotService getGotService();
}
