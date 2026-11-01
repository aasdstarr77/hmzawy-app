package com.hamzawysat.app.core.network
import com.hamzawysat.app.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
@Module @InstallIn(SingletonComponent::class) object NetworkModule{@Provides @Singleton fun client():OkHttpClient=OkHttpClient.Builder().connectTimeout(20,TimeUnit.SECONDS).readTimeout(30,TimeUnit.SECONDS).addInterceptor(HttpLoggingInterceptor().apply{level=if(BuildConfig.DEBUG)HttpLoggingInterceptor.Level.BASIC else HttpLoggingInterceptor.Level.NONE}).build();@Provides @Singleton fun api(client:OkHttpClient):ApiService=Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)}
