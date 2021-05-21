package com.template.androidtemplate.di.module

import android.content.Context
import androidx.annotation.NonNull
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.template.androidtemplate.BuildConfig
import com.template.androidtemplate.data.api.*
import com.template.androidtemplate.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule() {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }


    @Provides
    fun providesBaseUrl() = BuildConfig.BASE_URL


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        val supportInterceptor = SupportInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        if (BuildConfig.DEBUG) {
            return OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .followRedirects(true)
                .followSslRedirects(true)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(supportInterceptor)
                .build()
        } else {
            return OkHttpClient
                .Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .followRedirects(true)
                .followSslRedirects(true)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(supportInterceptor)
                .build()
        }
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)



    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper



    @Provides
    @Singleton
    @NonNull
    fun provideGson(): Gson {
        return Gson()
    }


}
