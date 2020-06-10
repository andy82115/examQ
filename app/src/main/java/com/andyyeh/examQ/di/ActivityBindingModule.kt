package com.andyyeh.examQ.di

import com.andyyeh.examQ.launchActivity.LaunchActivity
import com.andyyeh.examQ.launchActivity.LaunchActivityModule
import com.andyyeh.examQ.mainActivity.MainActivity
import com.andyyeh.examQ.mainActivity.MainActivityModule
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