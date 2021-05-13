package com.app.android.exam.ui.base

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.app.android.exam.R
import com.app.android.exam.data.remote.ErrorBody
import com.app.android.exam.di.AppViewModelFactory
import com.app.android.exam.ui.fragment.PersonDetailsFragment
import com.app.android.exam.ui.fragment.PersonListFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    protected abstract fun getLayoutId(): Int

    protected abstract fun initialize()

    abstract fun observedData()

    private var baseViewModel: BaseViewModel? = null

    private var viewDataBinding: ViewDataBinding? = null

    private var context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        initialize()
    }

    override fun onStart() {
        super.onStart()
        baseViewModel?.observeCommonEvent(this)
    }

    override fun onStop() {
        super.onStop()
        baseViewModel?.removeObservers(this)
        context.cacheDir?.deleteRecursively()
    }

    override fun onPause() {
        super.onPause()
        context.cacheDir?.deleteRecursively()
    }

    override fun onBackPressed() {
        val msg =
            when (supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.childFragmentManager?.fragments?.get(
                0
            )) {
                is PersonListFragment -> "Are you sure you want to exit?"
                is PersonDetailsFragment -> "Are you sure you want to leave this page?"
                else -> ""
            }

        if (msg.isEmpty()) {
            super.onBackPressed()
        } else {
            showDefaultDialog("", msg, DialogInterface.OnClickListener { _, _ ->
                if (msg.contains("want to exit")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        finishAndRemoveTask()
                    } else {
                        this.finishAffinity()
                    }
                } else {
                    super.onBackPressed()
                }
            })
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDefaultDialog(
        title: String, message: String,
        onClickListener: DialogInterface.OnClickListener
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok", onClickListener)
        builder.create().show()
    }

    private fun showDefaultErrorDialog(message: String) {
        showDefaultDialog(
            "Error",
            message,
            DialogInterface.OnClickListener { _, _ -> })
    }

    private fun showDefaultErrorDialog(
        message: String,
        onClickListener: DialogInterface.OnClickListener
    ) {
        showDefaultDialog("Error", message, onClickListener)
    }

    open fun noInternetConnection(throwable: Throwable) {
        throwable.printStackTrace()
        showDefaultErrorDialog(getString(R.string.global_msg_no_internet),
            DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
                finish()
            })
    }

    open fun loading(it: Boolean) {

    }

    open fun error(it: ErrorBody) {
        showDefaultErrorDialog(it.getFullMessage())
    }

    override fun onDestroy() {
        super.onDestroy()
        context.cacheDir?.deleteRecursively()
    }
}