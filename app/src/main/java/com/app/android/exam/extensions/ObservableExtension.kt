package com.app.android.exam.extensions

import com.app.android.exam.extensions.misc.common.SchedulersFacade
import com.app.android.exam.ui.base.BaseViewModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

// IO to MAIN
fun <T> Observable<T>.threadManageIoToUi(): Observable<T> =
    this.subscribeOn(SchedulersFacade.io())
        .observeOn(SchedulersFacade.ui())

fun <T> Flowable<T>.threadManageIoToUi(): Flowable<T> =
    this.subscribeOn(SchedulersFacade.io())
        .observeOn(SchedulersFacade.ui())

fun <T> Single<T>.threadManageIoToUi(): Single<T> =
    this.subscribeOn(SchedulersFacade.io())
        .observeOn(SchedulersFacade.ui())

// COMPUTATION to MAIN
fun <T> Observable<T>.threadManageComputationToUi(): Observable<T> =
    this.subscribeOn(SchedulersFacade.computation())
        .observeOn(SchedulersFacade.ui())

fun <T> Flowable<T>.threadManageComputationToUi(): Flowable<T> =
    this.subscribeOn(SchedulersFacade.computation())
        .observeOn(SchedulersFacade.ui())

fun <T> Single<T>.threadManageComputationToUi(): Single<T> =
    this.subscribeOn(SchedulersFacade.computation())
        .observeOn(SchedulersFacade.ui())

fun <T> Observable<T>.addDefaultDoOn(baseViewModel: BaseViewModel): Observable<T> =
    this.doOnComplete { baseViewModel.loading.postValue(false) }
        .doOnSubscribe { baseViewModel.loading.postValue(true) }

fun <T : Any> Observable<T>.defaultSubscribeBy(
    baseViewModel: BaseViewModel,
    onNext: (T) -> Unit
): Disposable =
    subscribe(onNext,
        {
            it.printStackTrace()
            it.postError(baseViewModel)
        },
        {})
