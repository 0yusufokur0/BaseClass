package com.resurrection.baseclass.base.extra

/*

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.resurrection.baseclass.R


abstract class BaseBottomSheetFragment<VDB : ViewDataBinding> : BottomSheetDialogFragment(),
    LifecycleObserver {

    private var _binding: VDB? = null
    val binding get() = _binding!!
    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun init(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       //TODO: for theme remove this comment // setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme) // look the end of page for theme
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        return _binding?.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

 CustomBottomSheetDialogTheme
<style name="CustomBottomSheetDialogTheme" parent="Theme.Design.Light.BottomSheetDialog">
    <item name="bottomSheetStyle">@style/CustomBottomSheetStyle</item>
</style>

<style name="CustomBottomSheetStyle" parent="Widget.Design.BottomSheet.Modal">
    <item name="android:background">@drawable/bottom_sheet_bg</item>
</style>



 bottom_sheet_bg
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">

<item
    android:bottom="10dp"
    android:left="10dp"
    android:right="10dp"
    android:top="10dp">
    <shape>
        <corners
            android:bottomLeftRadius="18dp"
            android:bottomRightRadius="18dp"
            android:topLeftRadius="18dp"
            android:topRightRadius="18dp" />
        <gradient
            android:angle="90"
            android:centerColor="@color/black"
            android:endColor="@color/white"
            android:startColor="@color/black" />
    </shape>
</item>

</layer-list>
*/
