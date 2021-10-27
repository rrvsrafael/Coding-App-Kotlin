package com.mr.codingapp2.di.component

import com.mr.codingapp2.MyApplication
import com.mr.codingapp2.backend.di.module.ApiModule
import com.mr.codingapp2.di.AppScope
import com.mr.codingapp2.di.module.AppModule
import com.mr.codingapp2.di.module.FlipperModule
import com.mr.codingapp2.di.module.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@AppScope
@Singleton
@Component(
    modules = [
        AppModule::class,
        FlipperModule::class,
        ApiModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(app: MyApplication)

    fun newActivityComponentBuilder(): ActivityComponent.Builder

    fun newFragmentComponentBuilder(): FragmentComponent.Builder
}
