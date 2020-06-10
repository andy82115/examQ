package com.andyyeh.template.mainActivity

import com.andyyeh.template.Configuration
import com.andyyeh.template.mvvmBase.BaseViewModel

class MainActivityViewModel(repository: MainActivityRepository) : BaseViewModel() {

    var isSearchBarExtend = false

    private val TAG = MainActivityViewModel::class.java.simpleName
    private val DEBUG = Configuration.DEBUG

    private val mRepository = repository
}