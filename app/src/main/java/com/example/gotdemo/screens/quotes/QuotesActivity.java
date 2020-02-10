package com.example.gotdemo.screens.quotes;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gotdemo.DemoApplication;
import com.example.gotdemo.R;
import com.example.gotdemo.model.Quote;

import javax.inject.Inject;

public class QuotesActivity extends AppCompatActivity implements QuotesActivityView {

    @Inject
    QuotesActivityPresenter quotesActivityPresenter;

    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView characterTextView;
    private TextView quoteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        DaggerQuotesActivityComponent.builder()
                .demoApplicationComponent(DemoApplication.get(this).component())
                .build()
                .injectQuotesActivity(this);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        characterTextView = findViewById(R.id.text_view_character);
        quoteTextView = findViewById(R.id.text_view_quote);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            quotesActivityPresenter.onQuotesRefresh();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        quotesActivityPresenter.setQuotesActivityView(this);
        quotesActivityPresenter.onQuotesRefresh();
    }

    @Override
    protected void onPause() {
        super.onPause();
        quotesActivityPresenter.detatch();
    }

    @Override
    public void showQuote(Quote quote) {
        swipeRefreshLayout.setRefreshing(false);
        characterTextView.setText(quote.getCharacter());
        quoteTextView.setText(quote.getQuote());
    }

    @Override
    public void showErrorMessage(String message) {
        Toast toast = Toast.makeText(
                this,
                message,
                Toast.LENGTH_SHORT
        );
        toast.show();
    }
}
