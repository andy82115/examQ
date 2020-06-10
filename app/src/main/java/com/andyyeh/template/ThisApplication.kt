package com.andyyeh.template

import com.andyyeh.template.di.AppComponent
import com.andyyeh.template.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ThisApplication constructor() : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent: AppComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)

        return appComponent
    }
}