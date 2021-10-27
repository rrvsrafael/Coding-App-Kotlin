package com.mr.codingapp2.di.module

import androidx.fragment.app.Fragment
import com.mr.codingapp2.di.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    @FragmentScope
    fun provideFragment(): Fragment = fragment
}
