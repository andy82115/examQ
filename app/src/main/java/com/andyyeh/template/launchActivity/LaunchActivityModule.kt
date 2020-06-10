package com.andyyeh.template.launchActivity

import com.andyyeh.template.databinding.ActivityLaunchBinding
import com.andyyeh.template.di.ActivityScoped
import dagger.Module
import dagger.Provides

@Module
class LaunchActivityModule {

    @ActivityScoped
    @Provides fun ViewModel() : LaunchActivityViewModel{
        return LaunchActivityViewModel()
    }
}