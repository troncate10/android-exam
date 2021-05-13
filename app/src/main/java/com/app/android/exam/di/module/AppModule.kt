package com.app.android.exam.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

}