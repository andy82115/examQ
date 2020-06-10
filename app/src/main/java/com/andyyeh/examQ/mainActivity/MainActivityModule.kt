package com.andyyeh.examQ.mainActivity

import com.andyyeh.examQ.di.ActivityScoped
import com.andyyeh.examQ.di.BasicInfo
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