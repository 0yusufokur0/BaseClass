package com.resurrection.baseclass.base.dialog

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.resurrection.baseclass.R


open class BaseDialog(private val context: Activity, layoutId: Int) {

    protected var dialog: AlertDialog? = null
    protected var view: View? = null

    init {
        view = context.layoutInflater.inflate(layoutId, null)

        dialog = AlertDialog.Builder(context)
            .setView(view)
            .setCancelable(false)
            .create()

        val color = ColorDrawable(Color.TRANSPARENT)
        dialog?.window?.let {
            /*it.attributes.windowAnimations = R.style.DialogScale */ //Todo: look down
            it.setBackgroundDrawable(color)

           /* <style name="DialogScale">
            <item name="android:windowEnterAnimation">@anim/scale_in</item>
            <item name="android:windowExitAnimation">@anim/scale_out</item>
            </style>*/
        }
    }


    fun show() {
        dialog?.let {
            context.runOnUiThread {
                it.show()
            }
        }
    }

    fun dismiss() {
        dialog?.let {
            context.runOnUiThread {
                it.dismiss()
            }
        }
    }


    fun clear() {
        dismiss()
        dialog = null
        view = null
    }

}