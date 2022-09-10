package com.example.cuanku.utils

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.cuanku.R
import com.example.cuanku.databinding.LoadingDialogBinding

class LoadingDialog : DialogFragment() {

    private lateinit var binding: LoadingDialogBinding

    private var shown: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.loading_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LoadingDialogBinding.bind(view)
        isCancelable = false
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.WRAP_CONTENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width, height)
            dialog.window?.setGravity(Gravity.CENTER)
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (shown) return
        super.show(manager, tag)
        shown = true
    }

    override fun onDismiss(dialog: DialogInterface) {
        shown = false
        super.onDismiss(dialog)
    }

}