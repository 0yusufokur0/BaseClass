package com.resurrection.baseclass.base.util

import android.content.Context
import android.graphics.Bitmap

object BitmapUtils {

    fun saveImage(context: Context, b: Bitmap, imageName: String) {
        context.openFileOutput(imageName,Context.MODE_PRIVATE)?.use {
            b.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
    }
}