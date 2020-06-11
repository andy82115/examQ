package com.example.andynovelapi.internetApi

interface ClimateObserverListener{

    interface ObserverOnNextListener<T>{
        fun onNext(t: T)
    }

    interface ObserverOnErrorListener{
        fun onError()
    }

    interface ProgressCancelListener{
        fun onCancelProgress()
    }
}