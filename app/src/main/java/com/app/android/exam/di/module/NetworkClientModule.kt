package com.app.android.exam.di.module

import com.app.android.exam.extensions.misc.common.SchedulersFacade
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */
@Module
class NetworkClientModule {
    @Singleton
    @Provides
    internal fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    internal fun provideHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        val timeout: Long = 300

        val builder = OkHttpClient.Builder()
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(false)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
//            .addInterceptor(logging) //uncomment to show logs

        return builder.build()
    }

    @Singleton
    @Provides
    internal fun provideGson(): Gson {
        // JsonDeserializer
        return GsonBuilder()
//            .registerTypeAdapter(SearchMtcnResponse::class.java, GsonMtcnResponseAdapter())
//            .registerTypeAdapter(Date::class.java, GsonDateAdapter())
//            .registerTypeAdapter(ExistingCustomerResponse::class.java, GsonCustomerDetailsAdapter())
//            .registerTypeAdapter(PaymentTransaction::class.java, GsonPaymentTransactionAdapter())
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    internal fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(SchedulersFacade.io()))
            .client(client)
            .build()
    }
}