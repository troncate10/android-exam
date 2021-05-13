package com.app.android.exam.di.module

import com.app.android.exam.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity

}