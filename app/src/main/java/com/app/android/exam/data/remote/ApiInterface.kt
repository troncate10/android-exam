package com.app.android.exam.data.remote

import com.app.android.exam.data.remote.model.ContactPerson
import com.app.android.exam.data.remote.model.Person
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */
interface ApiInterface {

    @GET("api/?nat=us&results=10")
    fun getPersonList(): Observable<Response<DataWrapper<MutableList<Person>>>>

    @GET("api/?nat=au&results=10")
    fun getContactPersonList(): Observable<Response<DataWrapper<MutableList<ContactPerson>>>>
}