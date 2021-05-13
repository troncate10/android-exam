package com.app.android.exam

import com.app.android.exam.di.DaggerAppComponent
import android.content.Context
import androidx.multidex.MultiDex
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */
class App : DaggerApplication(){
    companion object {
        var instance: App? = null
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }

}