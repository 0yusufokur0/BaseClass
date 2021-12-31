package com.resurrection.baseclass.ui

import android.os.Bundle
import com.resurrection.baseclass.R
import com.resurrection.baseclass.databinding.ActivityMainBinding
import com.resurrection.baseclass.base.core.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>
    (R.layout.activity_main,MainActivityViewModel::class.java) {

    override fun init(savedInstanceState: Bundle?) {

        var user = User("张三", 123456)

        println(getModelFieldValue(user,"name"))
    }

    fun  getModelFieldValue(model:Any,fieldName:String):Any?{
        val clazz = model.javaClass
        val field = clazz.getDeclaredField(fieldName)
        field.isAccessible = true
        return field.get(model)
    }


    data class User(val name:String,val age:Int)

}

