package com.example.footballcardgame.retrofit

import android.content.Context
import com.example.footballcardgame.common.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient(private val context: Context) {

    fun api(): Api {
        val mockFitInterceptor = provideMockInterceptor()
        val okHttpClient = provideOkHttpClient(mockFitInterceptor)
        val retrofit = provideRetrofit(okHttpClient)
        return retrofit.create(Api::class.java)
    }

    private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()



    private fun provideOkHttpClient(mockFitInterceptor: Interceptor) = OkHttpClient.Builder()
        .addInterceptor(mockFitInterceptor)
        .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
        .build()

    fun provideMockInterceptor(): Interceptor = MockInterceptor(context)
}