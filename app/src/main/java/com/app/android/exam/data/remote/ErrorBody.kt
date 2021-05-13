package com.app.android.exam.data.remote

import com.google.gson.JsonElement
import com.google.gson.JsonObject

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

class ErrorBody (
    override var message: String?,
    private var errors: Errors? = null
) : Throwable() {
    fun getFullMessage(): String {
        errors?.let { errors ->
            message =
                try {
                    if ( errors.detail.isJsonObject ) {
                        val jsonError = errors.detail as JsonObject
                        if (jsonError.has("message")) jsonError.get("message").asString
                        else null
                    }
                    else errors.detail.asString
                } catch (e: Exception ) {
                    e.printStackTrace()
                    "An error occurred"
                }
        }
        if ( message == "null" ) message = null
        return message ?: "An error occurred"
    }
    data class Errors(val status: String, val detail: JsonElement)
}