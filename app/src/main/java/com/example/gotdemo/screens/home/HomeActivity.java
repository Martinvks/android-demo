package com.example.gotdemo.screens.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.gotdemo.R;
import com.example.gotdemo.screens.quotes.QuotesActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getQuoteButton = findViewById(R.id.button_get_quote);
        getQuoteButton.setOnClickListener(this::onQuotesButtonClicked);
    }

    private void onQuotesButtonClicked (View view) {
        Intent intent = new Intent(this, QuotesActivity.class);
        startActivity(intent);
    }
}
