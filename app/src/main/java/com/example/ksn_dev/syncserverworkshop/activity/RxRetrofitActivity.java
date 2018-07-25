package com.example.ksn_dev.syncserverworkshop.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.ksn_dev.syncserverworkshop.R;
import com.example.ksn_dev.syncserverworkshop.adapter.SkillDataAdapter;
import com.example.ksn_dev.syncserverworkshop.dao.ItemCollectionDao;
import com.example.ksn_dev.syncserverworkshop.dao.RxJavaUnitTest;
import com.example.ksn_dev.syncserverworkshop.dao.SkillItemCollectionDao;
import com.example.ksn_dev.syncserverworkshop.manager.HttpItemManager;
import com.example.ksn_dev.syncserverworkshop.manager.HttpSkillManager;
import com.example.ksn_dev.syncserverworkshop.manager.RetrofitClient;
import com.example.ksn_dev.syncserverworkshop.manager.http.SkillAPI;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Retrofit;

public class RxRetrofitActivity extends AppCompatActivity {
    private RecyclerView rcv;
    private SkillDataAdapter mAdapter;
    private SkillAPI mSkillApi;
    private Retrofit mRetrofit;
    private CompositeDisposable mCompositeDisposable;
    private Observable<SkillItemCollectionDao> api;
    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_retrofit);

        initInstance();
    }

    @Override
    protected void onDestroy() {
        /*if (mDisposable != null && mDisposable.isDisposed()) {
            mDisposable.dispose();
        }*/
        //mCompositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        mCompositeDisposable.clear();
        super.onStop();
    }

    private void initInstance() {
        rcv = findViewById(R.id.recyclerView_rxRetrofit);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        mCompositeDisposable = new CompositeDisposable();

        //fetchData();
        //fetchDataByDisposable();
        api = HttpSkillManager.getInstance().getApi().feedSkillItem();
        final Observable<ItemCollectionDao> itemApi =
                HttpItemManager.getInstance().getApi().feedAt500px();

        mCompositeDisposable.addAll(
                getSkillItems(),
                itemApi.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<ItemCollectionDao>() {
                            @Override
                            public void accept(ItemCollectionDao itemCollectionDao) throws Exception {
                                Log.d("Rx", "OnNext " +
                                        itemCollectionDao.getData().get(2).getCaption());
                                Toast.makeText(RxRetrofitActivity.this,
                                        itemCollectionDao.getData().get(2).getCaption(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("Rx", "OnError " + throwable.getMessage());
                                Toast.makeText(RxRetrofitActivity.this,
                                        "OnError " + throwable.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                )
        );

    }

    @NonNull
    private Disposable getSkillItems() {
        return api.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<SkillItemCollectionDao>() {
                            @Override
                            public void accept(SkillItemCollectionDao dao) throws Exception {
                                mAdapter = new SkillDataAdapter(dao);
                                rcv.setAdapter(mAdapter);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("Rx", "OnError " + throwable.getMessage());
                                Toast.makeText(RxRetrofitActivity.this,
                                        "OnError " + throwable.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                );
    }

    private void fetchDataByDisposable() {
        mDisposable = HttpSkillManager.getInstance().getApi().feedSkillItem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<SkillItemCollectionDao>() {
                            @Override
                            public void accept(SkillItemCollectionDao dao) throws Exception {
                                Log.d("Rx", "onNext " +
                                        dao.getData().get(1).getTitle());
                                mAdapter = new SkillDataAdapter(dao);
                                //mAdapter.notifyDataSetChanged();
                                rcv.setAdapter(mAdapter);
                                rcv.setLayoutManager(
                                        new LinearLayoutManager(getBaseContext()));

                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("Rx", "onError " +
                                        throwable.getMessage());
                            }
                        }
                );
    }

    private void fetchData() {
        mRetrofit = RetrofitClient.getInstance();
        mSkillApi = mRetrofit.create(SkillAPI.class);

        mCompositeDisposable.add(
                mSkillApi.feedSkillItem()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                new Consumer<SkillItemCollectionDao>() {
                                    @Override
                                    public void accept(SkillItemCollectionDao dao) throws Exception {
                                        Log.d("Rx", "Dao " +
                                                dao.getData().get(2).getTitle());
                                    }
                                }
                        )
        );
    }


}
