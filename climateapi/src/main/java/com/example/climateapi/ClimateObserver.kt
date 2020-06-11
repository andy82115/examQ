package com.example.andynovelapi.internetApi

import android.util.Log
import com.example.climateapi.Configuration
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class ClimateObserver<T> (listenerOnNext: ClimateObserverListener.ObserverOnNextListener<T>) : Observer<T>, ClimateObserverListener.ProgressCancelListener {

    private val TAG = ClimateObserver::class.java.simpleName

    private val DEBUG = Configuration.DEBUG

    private var mListenerOnNext = listenerOnNext
    private var mListenerOnError: ClimateObserverListener.ObserverOnErrorListener? = null

    private var disposable: Disposable? = null

    constructor(listenerOnNext: ClimateObserverListener.ObserverOnNextListener<T>,
                listenerOnError: ClimateObserverListener.ObserverOnErrorListener) : this(listenerOnNext){
        this.mListenerOnError = listenerOnError
    }

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {
        this.disposable = d
        if (DEBUG) Log.e(TAG, "OnSubscribe")
    }

    override fun onNext(t: T) {
        mListenerOnNext.onNext(t)
        if (DEBUG) Log.e(TAG, "OnNext = $t")
    }

    override fun onError(e: Throwable) {
        if (mListenerOnError != null) mListenerOnError!!.onError()
        if (DEBUG) Log.e(TAG, "OnError = $e")
    }

    override fun onCancelProgress() {
        if (disposable!= null && !disposable!!.isDisposed) disposable!!.dispose()
        if (DEBUG) Log.e(TAG, "OnError")
    }
}