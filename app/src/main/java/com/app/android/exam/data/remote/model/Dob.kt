package com.app.android.exam.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

@Parcelize
class Dob(
    var date: Date? = null
) : Parcelable