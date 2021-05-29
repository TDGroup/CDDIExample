package com.tdgroup.cicdexample.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

 object APIClient {

    private const val BASE_URL = "https://api.themoviedb.org/"
    private lateinit var retrofit: Retrofit

    val client: Retrofit
        get() {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val httpClient = OkHttpClient().newBuilder()
                .addInterceptor(logging)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build()


            if (!APIClient::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                    .client(httpClient)
                    .build()
            }
            return retrofit
        }


}