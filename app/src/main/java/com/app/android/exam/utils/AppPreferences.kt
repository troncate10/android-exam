package com.app.android.exam.utils

import android.content.Context
import com.app.android.exam.App
import com.app.android.exam.data.remote.model.ContactPerson
import com.app.android.exam.data.remote.model.Person
import com.google.gson.Gson

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

class AppPreferences {
    companion object {

        private const val PERSON = "PREFS_PERSON"
        private const val CONTACT_PERSON = "PREFS_CONTACT_PERSON"

        fun savePersonList(person: MutableList<Person>) {
            val prefs = App.instance!!.getSharedPreferences(
                App.instance!!.packageName,
                Context.MODE_PRIVATE
            )
            val personList = Gson().toJson(person)
            prefs.edit().putString(PERSON, personList).apply()
        }

        fun getPersonList(): MutableList<Person>? {
            return try {
                val prefs = App.instance!!.getSharedPreferences(
                    App.instance!!.packageName,
                    Context.MODE_PRIVATE
                )
                val personList = prefs.getString(PERSON, "")
                Gson().fromJson(personList, Array<Person>::class.java)
                    .toMutableList()
            } catch (ex: Exception) {
                null
            }
        }

        fun saveContactPersonList(person: MutableList<ContactPerson>) {
            val prefs = App.instance!!.getSharedPreferences(
                App.instance!!.packageName,
                Context.MODE_PRIVATE
            )
            val contactPersonList = Gson().toJson(person)
            prefs.edit().putString(CONTACT_PERSON, contactPersonList).apply()
        }

        fun getContactPersonList(): MutableList<ContactPerson>? {
            return try {
                val prefs = App.instance!!.getSharedPreferences(
                    App.instance!!.packageName,
                    Context.MODE_PRIVATE
                )
                val contactPersonList = prefs.getString(CONTACT_PERSON, "")
                Gson().fromJson(contactPersonList, Array<ContactPerson>::class.java)
                    .toMutableList()
            } catch (ex: Exception) {
                null
            }
        }
    }
}