package app.weatherapp.di.module

import android.content.Context
import app.weatherapp.BuildConfig
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


/**
 * provides instance of objects used for performing network operations
 */
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun providesOkHttpClient(context: Context): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong() // 5 MB
        val okHttpClientBuilder = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            /*.addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .method(original.method(), original.body())
                    .cacheControl(CacheControl.Builder().maxAge(10, TimeUnit.MINUTES).build())
                    .build()
                chain.proceed(request)
            }*/.dispatcher(Dispatcher(Executors.newSingleThreadExecutor()))
            .cache(Cache(context.cacheDir, cacheSize))
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(logging)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    @Singleton
    fun provideRetrofitInterface(@Named("baseUrl") url: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(okHttpClient)
            .build()
    }
}