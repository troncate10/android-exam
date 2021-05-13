package com.app.android.exam.di

import android.app.Application
import com.app.android.exam.App
import com.app.android.exam.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        DataSourceModule::class,
        NetworkClientModule::class,
        NetworkApiModule::class
    ]
)

interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}