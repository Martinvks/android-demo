package com.example.gotdemo.dagger.modules;

import com.example.gotdemo.dagger.scopes.DemoApplicationScope;
import com.example.gotdemo.network.GotService;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class GotServiceModule {

    @DemoApplicationScope
    @Provides
    public GotService gotService(Retrofit gotRetrofit) {
        return gotRetrofit.create(GotService.class);
    }

    @DemoApplicationScope
    @Provides
    public Retrofit retrofit(
            Gson gson,
            OkHttpClient okHttpClient,
            RxJava2CallAdapterFactory rxJava2CallAdapterFactory
    ) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient)
                .baseUrl("https://got-quotes.herokuapp.com")
                .build();
    }
}
