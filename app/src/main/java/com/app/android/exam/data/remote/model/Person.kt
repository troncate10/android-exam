package com.app.android.exam.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

@Parcelize
class Person(
    var name: Name? = null,
    var dob: Dob? = null,
    var email: String? = null,
    var phone: String? = null,
    var location: Location? = null
) : Parcelable