package com.aking.easilyemojis.base

import android.app.ProgressDialog
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * Created by Rick at 2022/11/27 20:22
 * God bless my code.
 */
abstract class BaseFragment(@LayoutRes val layoutRes: Int) : Fragment(layoutRes), IUiView {
    private var progressDialog: ProgressDialog? = null

    override fun showLoading() {
        if (progressDialog == null) progressDialog = ProgressDialog(requireActivity())
        progressDialog?.show( )
    }

    override fun dismissLoading() {
        progressDialog?.takeIf { it.isShowing }?.dismiss()
    }

}