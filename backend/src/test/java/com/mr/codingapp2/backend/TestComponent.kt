package com.mr.codingapp2.backend

import com.mr.codingapp2.backend.di.module.ApiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class
    ]
)
interface TestComponent {
    fun inject(app: ApiTest)
}
