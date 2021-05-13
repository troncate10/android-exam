package com.app.android.exam.ui.base

import android.app.Application
import androidx.lifecycle.*
import com.app.android.exam.data.remote.ErrorBody
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected val compositeDisposable = CompositeDisposable()

    val success = SingleLiveEvent<Any>()
    val loading = SingleLiveEvent<Boolean>()
    val error = SingleLiveEvent<ErrorBody>()
    val apiBlocked = SingleLiveEvent<Boolean>()
    val noInternetConnection = SingleLiveEvent<Throwable>()

    abstract fun observerToRemove(owner: LifecycleOwner)

    open fun observeCommonEvent(baseActivity: BaseActivity) {
        baseActivity.observedData()
        loading.observe(baseActivity, Observer { baseActivity.loading(it) })
        error.observe(baseActivity, Observer { baseActivity.error(it) })
        noInternetConnection.observe(
            baseActivity,
            Observer { baseActivity.noInternetConnection(it) })
    }

    open fun observeCommonEvent(baseFragment: BaseFragment) {
        baseFragment.observedData()
        loading.observe(baseFragment, Observer { baseFragment.loading(it) })
        error.observe(baseFragment, Observer { baseFragment.error(it) })
        noInternetConnection.observe(
            baseFragment,
            Observer { baseFragment.noInternetConnection(it) })
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }

    open fun removeObservers(owner: LifecycleOwner) {
        observerToRemove(owner)
        loading.removeObservers(owner)
        error.removeObservers(owner)
        apiBlocked.removeObservers(owner)
        noInternetConnection.removeObservers(owner)
    }

    open fun removeObservers(owner: () -> Lifecycle) {
        loading.removeObservers(owner)
        error.removeObservers(owner)
        apiBlocked.removeObservers(owner)
        noInternetConnection.removeObservers(owner)
    }
}