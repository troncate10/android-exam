package com.app.android.exam.di.module

import com.app.android.exam.data.remote.ApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

@Module
class NetworkApiModule {
    @Provides
    internal fun provideApi(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}