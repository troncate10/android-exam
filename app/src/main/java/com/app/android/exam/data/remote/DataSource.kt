package com.app.android.exam.data.remote

import com.app.android.exam.data.remote.model.ContactPerson
import com.app.android.exam.data.remote.model.Person
import io.reactivex.Observable

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */
interface DataSource {

    fun getPersonList(): Observable<MutableList<Person>>

    fun getContactPersonList(): Observable<MutableList<ContactPerson>>

}
