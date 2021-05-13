package com.app.android.exam.extensions.misc.common

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

object SchedulersFacade {

    fun io(): Scheduler {
        return Schedulers.io()
    }

    fun computation(): Scheduler {
        return Schedulers.computation()
    }

    fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    fun single(): Scheduler {
        return Schedulers.single()
    }

}