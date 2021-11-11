package com.resurrection.baseclass.ui

import android.os.Bundle
import com.resurrection.baseclass.R
import com.resurrection.baseclass.base.core.BaseActivity
import com.resurrection.baseclass.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>
    (R.layout.activity_main,MainActivityViewModel::class.java) {

    override fun init(savedInstanceState: Bundle?) {

    }


}