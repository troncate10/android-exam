package com.app.android.exam.extensions

import com.app.android.exam.data.remote.ApiError
import com.app.android.exam.data.remote.ErrorBody
import com.app.android.exam.ui.base.BaseViewModel
import okhttp3.internal.http2.ConnectionShutdownException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.nio.channels.NotYetConnectedException
import javax.net.ssl.SSLHandshakeException

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */


fun <T : Any> Response<T>.postErrorBody(baseViewModel: BaseViewModel) {
    baseViewModel.loading.postValue(false)
    if (!this.isSuccessful) {
        baseViewModel.error.postValue(ApiError.parseError(this))
        return
    }
    baseViewModel.error.postValue(null)
}

fun Throwable.postError(baseViewModel: BaseViewModel) {
    baseViewModel.loading.postValue(false)
    if (this is NotYetConnectedException || this is UnknownHostException || this is SocketTimeoutException
        || this is ConnectionShutdownException || this is IOException
        || this is SSLHandshakeException
    ) {
        baseViewModel.noInternetConnection.postValue(this)
        return
    }

    if (this is ErrorBody) {
        baseViewModel.error.postValue(this)
    } else {
        val errorBody = ErrorBody(
            this.message!!
        )
        baseViewModel.error.postValue(errorBody)
    }
}

fun <T : Any> Response<T>.postErrorBody(message: String, baseViewModel: BaseViewModel) {
    baseViewModel.loading.postValue(false)
    if (this.isSuccessful) {
        baseViewModel.error.postValue(
            ErrorBody(message)
        )
        return
    }
    val errorBody = ErrorBody("An error occurred" )
    baseViewModel.error.postValue(errorBody)
}