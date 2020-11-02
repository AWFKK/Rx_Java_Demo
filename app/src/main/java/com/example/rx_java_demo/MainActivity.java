package com.example.rx_java_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.rx_java_demo.Adapter.Adapter;
import com.example.rx_java_demo.Adapter.HerosAdapter;
import com.example.rx_java_demo.Api.RetrofitClient;
import com.example.rx_java_demo.Model.Hero;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
//    private HerosAdapter adapter = new HerosAdapter();
    private RecyclerView gridRecycler;
    Adapter adapter;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        final ListView listView = (ListView) findViewById(R.id.list_view_repos);
        listView.setAdapter(adapter);*/

        gridRecycler = findViewById(R.id.recyclerview);
        GridLayoutManager mGrid = new GridLayoutManager(MainActivity.this, 2);
        gridRecycler.setLayoutManager(mGrid);
        gridRecycler.setHasFixedSize(true);

        final Button buttonSearch = (Button) findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                getHeroData();
            }
        });
    }

    @Override protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }


    private void getHeroData() {
        subscription =  RetrofitClient
                .getInstance()
                //.getMyApi().getHeroes()
                .getStarredRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Hero>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "In onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "In onError()");
                    }

                    @Override
                    public void onNext(List<Hero> heroes) {
                        Log.d(TAG, "In onNext()");
                        //Setting Adapter Here
                        adapter = new Adapter(MainActivity.this, heroes);
                        gridRecycler.setAdapter(adapter);

                        //adapter.setGitHubRepos(heroes);
                    }
                });
    }
}