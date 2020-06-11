package com.andyyeh.examQ.mainActivity

import com.andyyeh.examQ.di.ActivityScoped
import com.andyyeh.examQ.di.BasicInfo
import com.andyyeh.examQ.mainActivity.adapters.ClimateAdapter
import com.andyyeh.examQ.mainActivity.data.ClimateModel
import dagger.Module
import dagger.Provides

/**dependence inject module**/
@Module
class MainActivityModule{

    @ActivityScoped
    @Provides fun mainActivityRepository(basicInfo: BasicInfo) : MainActivityRepository{
        return MainActivityRepository(basicInfo)
    }

    @ActivityScoped
    @Provides fun mainActivityViewModel(mainActivityRepository: MainActivityRepository, climateModel: ClimateModel) : MainActivityViewModel{
        return MainActivityViewModel(mainActivityRepository, climateModel)
    }

    @ActivityScoped
    @Provides fun climateModel() : ClimateModel{
        return ClimateModel()
    }

    @ActivityScoped
    @Provides fun climateAdapter(climateModel: ClimateModel) : ClimateAdapter{
        return ClimateAdapter(climateModel)
    }
}