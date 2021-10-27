package com.mr.codingapp2.di.component

import android.app.Activity
import com.mr.codingapp2.di.ActivityScope
import com.mr.codingapp2.di.module.ActivityModule
import com.mr.codingapp2.ui.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [
        ActivityModule::class
    ]
)
interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun activityModule(module: ActivityModule): Builder
        fun build(): ActivityComponent
    }

    fun inject(activity: MainActivity)
}

fun ActivityComponent.Builder.buildAndInject(activity: Activity) {
    val component = activityModule(ActivityModule(activity)).build()

    when (activity) {
        is MainActivity -> component.inject(activity)
    }
}
