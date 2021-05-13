package com.app.android.exam.ui.adapter

import android.annotation.SuppressLint
import com.app.android.exam.data.remote.model.Person
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.android.exam.R
import com.app.android.exam.data.remote.model.ContactPerson
import kotlinx.android.synthetic.main.item_person.view.*

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

class PersonListRecyclerAdapter(
    private var onItemClickListener: OnItemClickListener,
    private var personList: MutableList<Person>,
    private var contactPersonList: MutableList<ContactPerson>,
) : RecyclerView.Adapter<PersonListRecyclerAdapter.ViewHolder>() {
    private lateinit var context: Context

    interface OnItemClickListener {
        fun onItemClick(person: Person, contactPerson: ContactPerson)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_person, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = personList[position]
        val contactPerson = contactPersonList[position]
        val fullName = "%s %s"
        holder.itemView.tv_name.text = String.format(
            fullName,
            person.name?.first,
            person.name?.last
        )
        holder.itemView.tv_email.text = person.email
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(person, contactPerson)
        }
    }

    override fun getItemCount() = personList.size
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}