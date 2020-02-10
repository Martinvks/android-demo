package com.example.gotdemo;

import com.example.gotdemo.model.Quote;
import com.example.gotdemo.network.GotService;
import com.example.gotdemo.screens.quotes.QuotesActivityPresenter;
import com.example.gotdemo.screens.quotes.QuotesActivityView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

public class QuotesActivityPresenterTest {

    @Mock
    GotService gotService;

    @Mock
    QuotesActivityView quotesActivityView;

    private QuotesActivityPresenter quotesActivityPresenter;

    private InOrder inOrder;

    @Before
    public void setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
        MockitoAnnotations.initMocks(this);
        inOrder = inOrder(quotesActivityView);

        quotesActivityPresenter = new QuotesActivityPresenter(gotService);
        quotesActivityPresenter.setQuotesActivityView(quotesActivityView);
    }

    @Test
    public void testGetRandomQuote() {
        Quote quote = getQuote();
        when(gotService.getRandomQuote()).thenReturn(Observable.fromCallable(() -> quote));

        quotesActivityPresenter.onQuotesRefresh();
        inOrder.verify(quotesActivityView).showQuote(eq(quote));
    }

    @Test
    public void showErrorMessageOnNetowrkError() {
        when(gotService.getRandomQuote()).thenReturn(Observable.error(new Exception()));

        quotesActivityPresenter.onQuotesRefresh();
        inOrder.verify(quotesActivityView).showErrorMessage("Ooops");
    }

    private Quote getQuote() {
        return new Quote("Do you lie awake at night fearing my gash?", "Varys");
    }
}