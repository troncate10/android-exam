package com.app.android.exam.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

@Parcelize
class Street(
    var number: String,
    var name: String
) : Parcelable