package com.andyyeh.examQ.di

import android.app.Application
import com.andyyeh.examQ.ThisApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (ActivityBindingModule::class),
    (ApplicationModule::class),
    (AndroidSupportInjectionModule::class)])
interface AppComponent : AndroidInjector<DaggerApplication>{

    fun inject( thisApplication: ThisApplication)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}