package com.app.android.exam.ui.base

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.android.exam.data.remote.ErrorBody
import com.app.android.exam.di.AppViewModelFactory
import com.app.android.exam.utils.UIUtilitiesFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.io.File
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * @author Alyssa Lois O. Tronco
 * @since  12/05/2021
 */


abstract class BaseFragment : UIUtilitiesFragment(), CoroutineScope {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    protected abstract fun getLayoutId(): Int

    protected abstract fun initialize(savedInstanceState: Bundle?)

    abstract fun initViews()

    abstract fun observedData()
    private var baseViewModel = mutableListOf<BaseViewModel>()

    private var viewDataBinding: ViewDataBinding? = null

    override val coroutineContext: CoroutineContext get() = Dispatchers.Main + Job()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return viewDataBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        baseViewModel.forEach {
            it.observeCommonEvent(this)
        }
    }

    override fun onStop() {
        super.onStop()
        baseViewModel.forEach { it.removeObservers(this) }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        context?.let { deleteCache(it) }
        context?.cacheDir?.deleteRecursively()
    }

    fun <T : BaseViewModel> initViewModel(viewModelClass: Class<T>): T {
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(viewModelClass)
        baseViewModel.add(viewModel)
        return viewModel
    }

    private fun showDefaultDialog(
        title: String, message: String,
        onClickListener: DialogInterface.OnClickListener?
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(false)
        builder.setPositiveButton("", onClickListener)
        builder.create().show()
    }

    private fun showDefaultErrorDialog(message: String) {
        showDefaultDialog("", message, null)
    }

    open fun loading(it: Boolean) {

    }

    open fun error(it: ErrorBody) {
        showDefaultErrorDialog(it.getFullMessage())
    }

    open fun noInternetConnection(throwable: Throwable) {
        throwable.printStackTrace()
        showDefaultErrorDialog("No internet")
    }

    fun initDefaultRecyclerView(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }

    open fun deleteCache(context: Context) {
        try {
            val dir: File = context.cacheDir
            deleteDir(dir)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    open fun deleteDir(dir: File?): Boolean {
        return if (dir != null && dir.isDirectory) {
            val children: Array<String> = dir.list()
            for (i in children.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
            dir.delete()
        } else if (dir != null && dir.isFile) {
            dir.delete()
        } else {
            false
        }
    }
}