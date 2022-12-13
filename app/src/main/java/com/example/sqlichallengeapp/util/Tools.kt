package com.example.sqlichallengeapp.util

import android.app.AlertDialog
import android.content.Context
import com.example.sqlichallengeapp.R

object Tools {

    fun setProgressDialog(context: Context?): AlertDialog? {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setView(R.layout.layout_loading_dialog)
        val alertDialog = builder.create()
        alertDialog.window!!.setBackgroundDrawableResource(R.color.transparent)
        return alertDialog
    }

}