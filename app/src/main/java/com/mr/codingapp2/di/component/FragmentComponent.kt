package com.mr.codingapp2.di.component

import androidx.fragment.app.Fragment
import com.mr.codingapp2.di.FragmentScope
import com.mr.codingapp2.di.module.FragmentModule
import com.mr.codingapp2.ui.home.HomeFragment
import com.mr.codingapp2.ui.repo.RepoFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(
    modules = [
        FragmentModule::class
    ]
)
interface FragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        fun fragmentModule(module: FragmentModule): Builder
        fun build(): FragmentComponent
    }

    fun inject(fragment: HomeFragment)
    fun inject(fragment: RepoFragment)
}

fun FragmentComponent.Builder.buildAndInject(fragment: Fragment) {
    val component = fragmentModule(FragmentModule(fragment)).build()

    when (fragment) {
        is HomeFragment -> component.inject(fragment)
        is RepoFragment -> component.inject(fragment)
    }
}
