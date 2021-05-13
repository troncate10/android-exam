package com.app.android.exam.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.android.exam.R
import com.app.android.exam.ui.base.BaseActivity
import com.app.android.exam.ui.base.BaseFragment
import com.app.android.exam.ui.view_model.PersonListViewModel
import com.app.android.exam.utils.AppPreferences
import kotlinx.android.synthetic.main.fragment_splash_screen.*

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

class SplashScreenFragment : BaseFragment() {

    private val personListViewModel by lazy { initViewModel(PersonListViewModel::class.java) }

    override fun getLayoutId(): Int = R.layout.fragment_splash_screen

    override fun initialize(savedInstanceState: Bundle?) {
        if (AppPreferences.getPersonList() == null) {
            personListViewModel.getPerson()
        } else {
            nextFragment()
        }
    }

    override fun initViews() {

    }

    override fun observedData() {

        personListViewModel.person.observe(this, Observer {
            progress_bar.visibility = View.VISIBLE
            personListViewModel.getContactPerson()
        })

        personListViewModel.contactPerson.observe(this, Observer {
            nextFragment()
        })
    }

    private fun nextFragment() {
        progress_bar.visibility = View.GONE
        findNavController().navigate(SplashScreenFragmentDirections.actionNavSplashScreenToNavPersonList())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutId(), container, false)

    override fun onResume() {
        super.onResume()
        (activity as BaseActivity).supportActionBar?.hide()
        (activity as BaseActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}