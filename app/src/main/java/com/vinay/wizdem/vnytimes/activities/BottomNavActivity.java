package com.vinay.wizdem.vnytimes.activities;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.vinay.wizdem.vnytimes.R;
import com.vinay.wizdem.vnytimes.eventbus.BooksListToDetailsEvent;
import com.vinay.wizdem.vnytimes.eventbus.MessageEvent;
import com.vinay.wizdem.vnytimes.fragments.ArticleListFragment;
import com.vinay.wizdem.vnytimes.fragments.BookDetailedFragment;
import com.vinay.wizdem.vnytimes.fragments.BooksListFragment;
import com.vinay.wizdem.vnytimes.fragments.WebViewerFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.vinay.wizdem.vnytimes.R.id.fragment_container;

public class BottomNavActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Fragment articleListFragment = new ArticleListFragment();
    Fragment booksListFragment = new BooksListFragment();
    Fragment webViewerFragment = new WebViewerFragment();
    Fragment bookDetailedFragment = new BookDetailedFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getFragmentManager().beginTransaction()
                            .show(articleListFragment)
                            .hide(booksListFragment)
                            .remove(webViewerFragment)
                            .remove(bookDetailedFragment)
                            .commit();
                    return true;
                case R.id.navigation_books:
                    getFragmentManager().beginTransaction()
                            .show(booksListFragment)
                            .hide(articleListFragment)
                            .hide(webViewerFragment)
                            .remove(bookDetailedFragment)
                            .commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(savedInstanceState == null){
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(fragment_container,articleListFragment);
            fragmentTransaction.add(fragment_container,booksListFragment);
            fragmentTransaction.hide(booksListFragment);
            fragmentTransaction.commit();

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        startWebView(event.message);
    }
    private void startWebView(String url){
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        webViewerFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .add(fragment_container,webViewerFragment)
                .show(webViewerFragment)
                .hide(articleListFragment)
                .commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BooksListToDetailsEvent event){
        startBookHistory(event.title,event.author);
    }

    private void startBookHistory(String title, String author) {
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        bundle.putString("author",author);
        bookDetailedFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .add(fragment_container,bookDetailedFragment)
                .show(bookDetailedFragment)
                .hide(booksListFragment)
                .commit();
    }

}
