package com.app.android.exam.di.module

import androidx.lifecycle.ViewModel
import com.app.android.exam.di.ViewModelKey
import com.app.android.exam.ui.view_model.PersonListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PersonListViewModel::class)
    internal abstract fun bindPersonListViewModel(updateViewModel: PersonListViewModel): ViewModel
}