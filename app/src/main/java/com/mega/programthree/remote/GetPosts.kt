package com.mega.programthree.remote

import com.mega.programthree.model.response.PostModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GetPosts {

    @GET("posts")
    fun getData(): Single<Response<List<PostModel>>>

    companion object{

        private val BASE_URL: String ="https://jsonplaceholder.typicode.com/"
        private var retrofit: Retrofit? = null

        fun getPosts(): Retrofit? {
            if (retrofit == null) {

                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val builder = OkHttpClient.Builder()
                    .addInterceptor(interceptor)


                return Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()


            }
            return retrofit
        }
    }

}