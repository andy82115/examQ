package com.andyyeh.examQ.mainActivity

import com.andyyeh.examQ.Configuration
import com.andyyeh.examQ.mvvmBase.BaseViewModel

class MainActivityViewModel(repository: MainActivityRepository) : BaseViewModel() {

    var isSearchBarExtend = false

    private val TAG = MainActivityViewModel::class.java.simpleName
    private val DEBUG = Configuration.DEBUG

    private val mRepository = repository
}