package com.mr.codingapp2.di.module

import android.app.Application
import com.mr.codingapp2.di.AppScope
import com.mr.codingapp2.di.component.ActivityComponent
import com.mr.codingapp2.di.component.FragmentComponent
import dagger.Module
import dagger.Provides

@Module(
    subcomponents = [
        ActivityComponent::class,
        FragmentComponent::class
    ]
)
class AppModule(private val app: Application) {

    @Provides
    @AppScope
    fun provideApp(): Application = app
}
