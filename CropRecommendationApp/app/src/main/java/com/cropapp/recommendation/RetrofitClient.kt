package com.cropapp.recommendation

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    // 🔧 CHANGE THIS to your FastAPI server IP/address
    // For emulator accessing localhost: "http://10.0.2.2:8000/"
    // For real device on same WiFi: "http://YOUR_PC_IP:8000/"
    // For deployed server: "https://your-server.com/"
    private const val BASE_URL = "https://unfeigning-dorothy-unsurprisedly.ngrok-free.dev/"


    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    val instance: CropApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CropApiService::class.java)
    }
}
