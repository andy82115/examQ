package com.andyyeh.examQ.di

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.andyyeh.examQ.Configuration
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule{
    @Provides
    fun bindContext(application: Application) : Context{
        return application.applicationContext
    }

    @Provides
    fun basicInfo(application: Application) : BasicInfo{
        val appInfo = application.applicationInfo
        val labelRes = appInfo.labelRes
        val appName = if (labelRes == 0) appInfo.nonLocalizedLabel.toString() else application.getString(labelRes)
        return BasicInfo(appName, labelRes)
    }

    @Provides
    fun  sharedPreferences(application: Application) : SharedPreferences{
        return application.getSharedPreferences(Configuration.SHARE_PREFERENCES_NAME, MODE_PRIVATE)
    }
}