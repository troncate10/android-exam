package com.app.android.exam.data.remote.use_case

import com.app.android.exam.data.remote.DataSource
import com.app.android.exam.data.remote.Empty
import com.app.android.exam.data.remote.UseCase
import com.app.android.exam.data.remote.model.Person
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */
class GetPersonListUseCase
@Inject
constructor(private val dataSource: DataSource) : UseCase<Empty, MutableList<Person>>() {
    override fun executeUseCase(requestValues: Empty): Observable<MutableList<Person>> {
        return dataSource.getPersonList()
    }
}