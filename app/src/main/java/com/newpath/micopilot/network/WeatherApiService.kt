package com.newpath.micopilot.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://aviationweather.gov/adds/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logging)

private val retrofit = Retrofit.Builder()
    .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(
         Persister(AnnotationStrategy())))
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(httpClient.build())
    .build()

interface TafApiService {
    @GET("dataserver_current/httpparam?datasource=tafs&requesttype=retrieve&minLat=37&minLon=-122&maxLat=38.5&maxLon=-121&hoursBeforeNow=3")
    fun getProperties():
            Call<TafDataList>
}

object TafApi {
    val retrofitService : TafApiService by lazy {
        retrofit.create(TafApiService::class.java) }
}