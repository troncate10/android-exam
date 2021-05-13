package com.app.android.exam.data.remote.use_case

import com.app.android.exam.data.remote.DataSource
import com.app.android.exam.data.remote.Empty
import com.app.android.exam.data.remote.UseCase
import com.app.android.exam.data.remote.model.ContactPerson
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

class GetContactPersonUseCase
@Inject
constructor(private val dataSource: DataSource) : UseCase<Empty, MutableList<ContactPerson>>() {
    override fun executeUseCase(requestValues: Empty): Observable<MutableList<ContactPerson>> {
        return dataSource.getContactPersonList()
    }
}