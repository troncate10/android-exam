package com.app.android.exam.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

@Parcelize
class ContactPerson(
    var name: ContactPersonName? = null,
    var phone: String? = null
) : Parcelable