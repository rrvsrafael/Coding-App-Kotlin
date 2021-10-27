package com.mr.codingapp2.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mr.codingapp2.MyApplication
import com.mr.codingapp2.di.component.buildAndInject
import com.evernote.android.state.StateSaver

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).component
            .newActivityComponentBuilder().buildAndInject(this)
        super.onCreate(savedInstanceState)
        StateSaver.restoreInstanceState(this, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        StateSaver.saveInstanceState(this, outState)
    }

    fun <T : ViewModel> getViewModel(key: String, modelClass: Class<T>) =
        getViewModelProvider(this).get(key, modelClass)

    fun <T : ViewModel> getViewModel(modelClass: Class<T>) =
        getViewModelProvider(this).get(modelClass)

    fun getViewModelProvider(fragment: Fragment) =
        ViewModelProvider(fragment, (application as MyApplication).viewModelFactory)

    fun getViewModelProvider(activity: FragmentActivity) =
        ViewModelProvider(activity, (application as MyApplication).viewModelFactory)
}
