package com.app.android.exam.utils

import android.annotation.SuppressLint
import dagger.android.support.DaggerFragment
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

abstract class UIUtilitiesFragment : DaggerFragment() {

    companion object {
        const val HUMAN_READABLE_DATE = "MMMM dd, yyyy"
    }

    @SuppressLint("SimpleDateFormat")
    val sdf = SimpleDateFormat(HUMAN_READABLE_DATE)


    fun formatDate(dateToDisplay: Date): String? {
        return sdf.format(dateToDisplay)
    }

    fun formatAddress(
        number: String,
        name: String,
        city: String,
        state: String,
        country: String
    ): String? {
        val address = "%s %s %s %s, %s"
        return String.format(address, number, name, city, state, country)
    }

    fun formatContactPersonName(
        first: String,
        last: String
    ): String? {
        val name = "%s %s"
        return String.format(name, first, last)
    }

    open fun getAge(date: Date): String? {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()
        dob[calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)] =
            calendar.get(Calendar.DAY_OF_MONTH)
        var age = today[Calendar.YEAR] - dob[Calendar.YEAR]
        if (today[Calendar.DAY_OF_YEAR] < dob[Calendar.DAY_OF_YEAR]) {
            age--
        }
        val ageInt = age
        return ageInt.toString()
    }
}