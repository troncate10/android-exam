package com.app.android.exam.di.module

import com.app.android.exam.data.remote.DataRepository
import com.app.android.exam.data.remote.DataSource
import dagger.Binds
import dagger.Module

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

@Module
abstract class DataSourceModule {

    @Binds
    internal abstract fun provideDataRepository(dataRepository: DataRepository): DataSource
}