package com.app.android.exam.ui.view_model

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import com.app.android.exam.data.remote.Empty
import com.app.android.exam.data.remote.use_case.GetContactPersonUseCase
import com.app.android.exam.data.remote.use_case.GetPersonListUseCase
import com.app.android.exam.extensions.addDefaultDoOn
import com.app.android.exam.extensions.defaultSubscribeBy
import com.app.android.exam.extensions.threadManageIoToUi
import com.app.android.exam.ui.base.BaseViewModel
import com.app.android.exam.ui.base.SingleLiveEvent
import javax.inject.Inject

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */
class PersonListViewModel @Inject constructor(
    application: Application,
    private val getPersonListUseCase: GetPersonListUseCase,
    private val getContactPersonUseCase: GetContactPersonUseCase
) : BaseViewModel(application) {

    val person = SingleLiveEvent<Empty>()
    val contactPerson = SingleLiveEvent<Empty>()

    override fun observerToRemove(owner: LifecycleOwner) {
        person.removeObservers(owner)
        contactPerson.removeObservers(owner)
    }

    fun getPerson() {
        compositeDisposable.add(
            getPersonListUseCase.run(Empty())
                .threadManageIoToUi()
                .addDefaultDoOn(this)
                .defaultSubscribeBy(this) {
                    person.postValue(Empty())
                }
        )
    }

    fun getContactPerson() {
        compositeDisposable.add(
            getContactPersonUseCase.run(Empty())
                .threadManageIoToUi()
                .addDefaultDoOn(this)
                .defaultSubscribeBy(this) {
                    contactPerson.postValue(Empty())
                }
        )
    }
}