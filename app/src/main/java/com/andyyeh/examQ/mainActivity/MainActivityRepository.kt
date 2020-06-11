package com.andyyeh.examQ.mainActivity

import android.annotation.SuppressLint
import com.andyyeh.examQ.BeanTransformer
import com.andyyeh.examQ.di.BasicInfo
import com.andyyeh.examQ.mainActivity.data.ClimateBean
import com.example.andynovelapi.internetApi.ClimateApiMethods
import com.example.andynovelapi.internetApi.ClimateObserver
import com.example.andynovelapi.internetApi.ClimateObserverListener
import com.example.climateapi.NetClimateBean
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**build an contract interface allow me to write an unit test more easily
 * @see MainContract.Repository
 * the func of this class is to get result data from Internet and Database
 * because we should prevent ViewModel to call those heavy jobs
 * Doing in this way will make unit test more easily
 * **/
class MainActivityRepository(basicInfo: BasicInfo) : MainContract.Repository{

    /**some url POST need to input the application name**/
    private val mBasicInfo = basicInfo

    /**
     * @param location the location name of city
     * @param consumer the observer for ViewModel
     * Please notice the func "map" , the map will covert bean through
     * @see BeanTransformer which is a class to transform the data
     * **/
    override fun getDataFromInternet(location: String, consumer: Consumer<ArrayList<ClimateBean>>) {
        val onNextListener = object : ClimateObserverListener.ObserverOnNextListener<NetClimateBean>{
            @SuppressLint("CheckResult")
            override fun onNext(t: NetClimateBean) {
                Single.just(t)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(fun(netClimateBean: NetClimateBean): ArrayList<ClimateBean> {
                        return BeanTransformer.instance.netClimate2Climate(netClimateBean)
                    })
                    .subscribe(consumer)
            }
        }
        val climateObserver = ClimateObserver(onNextListener)
        ClimateApiMethods.getInstance(mBasicInfo.appName).getClimateData(climateObserver, location)
    }


}