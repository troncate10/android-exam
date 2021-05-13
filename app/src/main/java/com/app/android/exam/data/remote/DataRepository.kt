package com.app.android.exam.data.remote

import com.app.android.exam.data.remote.model.ContactPerson
import com.app.android.exam.data.remote.model.Person
import com.app.android.exam.utils.AppPreferences
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

class DataRepository
@Inject
constructor(private val apiInterface: ApiInterface) : DataSource {

    override fun getPersonList(): Observable<MutableList<Person>> {
        return apiInterface.getPersonList().flatMap {
            if (it.isSuccessful) Observable.just(it.body()!!.results!!)
            else Observable.error(ApiError.parseError(it))
        }.flatMap {
            AppPreferences.savePersonList(it)
            return@flatMap Observable.just(it)
        }
    }

    override fun getContactPersonList(): Observable<MutableList<ContactPerson>> {
        return apiInterface.getContactPersonList().flatMap {
            if (it.isSuccessful) Observable.just(it.body()!!.results!!)
            else Observable.error(ApiError.parseError(it))
        }.flatMap {
            AppPreferences.saveContactPersonList(it)
            return@flatMap Observable.just(it)
        }
    }
}