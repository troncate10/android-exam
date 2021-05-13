package com.app.android.exam.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

@Parcelize
class Location(
    var street: Street? = null,
    var city: String? = null,
    var state: String? = null,
    var country: String? = null,
) : Parcelable