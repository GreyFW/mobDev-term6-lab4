package com.example.bookbrowser.network.network

import com.example.bookbrowser.network.model.BookItem
import com.example.bookbrowser.network.model.BookResponse
import com.example.bookbrowser.network.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApi {
    @GET("volumes")
    suspend fun getBooks(
        @Query("q") query: String = "subject:fiction"
    ): BookResponse

    @GET("volumes/{bookId}")
    suspend fun getBookDetails(
        @Path("bookId") bookId: String
    ): BookItem
}

object NetworkModule {
    private const val BASE_URL = "https://www.googleapis.com/books/v1/"

    private val client = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(interceptor)
        }
    }.build()

    val api: BookApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookApi::class.java)
    }
}