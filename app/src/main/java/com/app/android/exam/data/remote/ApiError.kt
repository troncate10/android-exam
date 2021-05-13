package com.app.android.exam.data.remote

import android.util.Log
import com.google.gson.Gson
import retrofit2.Response

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

object ApiError {

    private const val TAG = "API_ERROR"

    fun <T> parseError(response: Response<T>): ErrorBody {
        val errorBody = ErrorBody(
            "An error occurred"
        )

        return try {
            val responseString = response.errorBody()!!.string()
            Log.e(TAG, responseString)

            Gson().fromJson(responseString, ErrorBody::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            errorBody
        }
    }

}