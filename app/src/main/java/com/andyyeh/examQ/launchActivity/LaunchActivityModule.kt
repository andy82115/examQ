package com.andyyeh.examQ.launchActivity

import com.andyyeh.examQ.databinding.ActivityLaunchBinding
import com.andyyeh.examQ.di.ActivityScoped
import dagger.Module
import dagger.Provides

@Module
class LaunchActivityModule {

    @ActivityScoped
    @Provides fun ViewModel() : LaunchActivityViewModel{
        return LaunchActivityViewModel()
    }
}