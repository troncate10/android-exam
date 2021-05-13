package com.app.android.exam.di.module

import com.app.android.exam.ui.fragment.PersonDetailsFragment
import com.app.android.exam.ui.fragment.PersonListFragment
import com.app.android.exam.ui.fragment.SplashScreenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun splashScreenFragment(): SplashScreenFragment

    @ContributesAndroidInjector
    internal abstract fun personListFragment(): PersonListFragment

    @ContributesAndroidInjector
    internal abstract fun personDetailsFragment(): PersonDetailsFragment
}