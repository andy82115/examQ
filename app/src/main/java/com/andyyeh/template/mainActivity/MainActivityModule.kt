package com.andyyeh.template.mainActivity

import com.andyyeh.template.di.ActivityScoped
import com.andyyeh.template.di.BasicInfo
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule{

    @ActivityScoped
    @Provides fun mainActivityRepository(basicInfo: BasicInfo) : MainActivityRepository{
        return MainActivityRepository(basicInfo)
    }

    @ActivityScoped
    @Provides fun mainActivityViewModel(mainActivityRepository: MainActivityRepository) : MainActivityViewModel{
        return MainActivityViewModel(mainActivityRepository)
    }
}