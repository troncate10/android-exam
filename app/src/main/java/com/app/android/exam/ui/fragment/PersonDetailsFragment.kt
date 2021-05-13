package com.app.android.exam.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.app.android.exam.R
import com.app.android.exam.data.remote.model.ContactPerson
import com.app.android.exam.data.remote.model.Person
import com.app.android.exam.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_person_details.*
import kotlinx.coroutines.launch

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

class PersonDetailsFragment : BaseFragment() {

    companion object {
        private const val ARGS_PERSON_DETAILS = "ARGS_PERSON_DETAILS"
        private const val ARGS_CONTACT_PERSON_DETAILS = "ARGS_CONTACT_PERSON_DETAILS"

        fun setArguments(
            personDetails: Person,
            contactPersonDetails: ContactPerson
        ): Bundle = bundleOf(
            ARGS_PERSON_DETAILS to personDetails,
            ARGS_CONTACT_PERSON_DETAILS to contactPersonDetails
        )
    }

    override fun getLayoutId(): Int = R.layout.fragment_person_details

    override fun initialize(savedInstanceState: Bundle?) {
        initViews()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutId(), container, false)

    override fun initViews() {
        launch {
            val personDetails = arguments?.getParcelable<Person>(
                ARGS_PERSON_DETAILS
            )

            val contactPersonDetails = arguments?.getParcelable<ContactPerson>(
                ARGS_CONTACT_PERSON_DETAILS
            )


            tv_first_name.text = personDetails!!.name!!.first
            tv_last_name.text = personDetails.name!!.last
            tv_birthday.text = formatDate(personDetails.dob!!.date!!)
            tv_age.text = getAge(personDetails.dob!!.date!!)
            tv_email.text = personDetails.email
            tv_mobile.text = personDetails.phone
            tv_address.text = formatAddress(
                personDetails.location!!.street!!.number,
                personDetails.location!!.street!!.name,
                personDetails.location!!.city!!,
                personDetails.location!!.state!!,
                personDetails.location!!.country!!

            )
            tv_contact_person.text = formatContactPersonName(
                contactPersonDetails!!.name!!.first!!,
                contactPersonDetails.name!!.last!!
            )
            tv_contact_person_phone.text = contactPersonDetails.phone
        }
    }

    override fun observedData() {

    }

}