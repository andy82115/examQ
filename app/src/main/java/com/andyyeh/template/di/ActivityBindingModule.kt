package com.andyyeh.template.di

import com.andyyeh.template.launchActivity.LaunchActivity
import com.andyyeh.template.launchActivity.LaunchActivityModule
import com.andyyeh.template.mainActivity.MainActivity
import com.andyyeh.template.mainActivity.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule constructor(){
    @ActivityScoped
    @ContributesAndroidInjector(modules = [(LaunchActivityModule::class)])
    abstract fun LaunchActivity() : LaunchActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun MainActivity() : MainActivity
}