package com.resurrection.imkb.ui.base.ui

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.resurrection.imkb.R

open class AppButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = android.R.attr.textViewStyle
) : MaterialButton(context, attrs, defStyleAttr) {
    val mcontext = context
    init {
        //setBackgroundResource(R.drawable.bottom_sheet_bg)
        //backgroundTintList = context.getColorStateList(R.color.black)
        background = context.getDrawable(R.drawable.bottom_sheet_bg)
    }
}