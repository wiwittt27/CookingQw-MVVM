package com.alawiyaa.cookingqw.utils

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.alawiyaa.cookingqw.R
import com.google.android.material.snackbar.Snackbar

open class BaseFragment : Fragment(){

    fun showErrorMessage(message : String, view:View){
        val text = HtmlCompat.fromHtml(
            "<font color=\"#FFFFFF\">$message</font>",
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        val snackBar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
        snackBar.view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_error))
        snackBar.show()
    }

    fun showInformationMessage(message : String, view:View){
        val text = HtmlCompat.fromHtml(
            "<font color=\"#FFFFFF\">$message</font>",
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        val snackBar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
        snackBar.view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_information))
        snackBar.show()
    }



}