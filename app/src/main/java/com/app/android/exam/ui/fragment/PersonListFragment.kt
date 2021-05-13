package com.app.android.exam.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.android.exam.R
import com.app.android.exam.data.remote.model.ContactPerson
import com.app.android.exam.data.remote.model.Person
import com.app.android.exam.ui.adapter.PersonListRecyclerAdapter
import com.app.android.exam.ui.base.BaseFragment
import com.app.android.exam.utils.AppPreferences
import kotlinx.android.synthetic.main.fragment_person_list.*

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

class PersonListFragment : PersonListRecyclerAdapter.OnItemClickListener, BaseFragment() {

    private var personDetails: MutableList<Person>? = null
    private lateinit var contactPersonDetails: MutableList<ContactPerson>

    override fun getLayoutId(): Int = R.layout.fragment_person_list

    override fun initialize(savedInstanceState: Bundle?) {
        initViews()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutId(), container, false)

    override fun initViews() {
        personDetails = AppPreferences.getPersonList()
        contactPersonDetails = AppPreferences.getContactPersonList()!!

        initDefaultRecyclerView(
            rv_person,
            PersonListRecyclerAdapter(
                this@PersonListFragment,
                personDetails!!,contactPersonDetails
            )
        )
    }

    override fun observedData() {

    }

    override fun onItemClick(person: Person, contactPerson: ContactPerson) {

        findNavController().navigate(
            R.id.nav_person_details,
            PersonDetailsFragment.setArguments(
                person, contactPerson
            )
        )
    }

}