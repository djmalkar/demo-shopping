package com.dipesh.demoshopping.dependencyinjection.modules;

import android.app.Application;

import com.dipesh.demoshopping.data.local.AppDatabase;
import com.dipesh.demoshopping.data.remote.ApiRetrofit;
import com.dipesh.demoshopping.dependencyinjection.ApplicationScope;

import java.util.concurrent.TimeUnit;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application){
        this.mApplication = application;
    }

    @Provides
    public Application getApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationScope
    public AppDatabase provideAppDatabase() {
        return Room.databaseBuilder(mApplication, AppDatabase.class, "shopping.db")
                .build();
    }

    @Provides
    @ApplicationScope
    public OkHttpClient getOkHttpClient(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build();

        return client;
    }

    @Provides
    @ApplicationScope
    public Retrofit getRetrofitBuilder(OkHttpClient client) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("https://stark-spire-93433.herokuapp.com")
                .build();
    }

    @Provides
    @ApplicationScope
    public ApiRetrofit getApiService(Retrofit retrofit) {
        return retrofit.create(ApiRetrofit.class);
    }
}
