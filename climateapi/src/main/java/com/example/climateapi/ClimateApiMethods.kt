package com.example.andynovelapi.internetApi

import com.example.andyutils.designPatternUtils.SingletonHolder
import com.example.climateapi.NetClimateBean
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.URLEncoder

class ClimateApiMethods private constructor(appName: String){

    private val baseUrl = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/"
    private val authorizationCode = "CWB-92E5B8FC-3072-4FD5-9BE9-ED4EDADC4598"
    private val format = "JSON"
    private val mApiService = ClimateApiManager(baseUrl, appName).getClimateApiService()

    companion object : SingletonHolder<ClimateApiMethods, String>(::ClimateApiMethods)

    private fun <T> apiSubscribe(observable: Observable<T>, observer: Observer<T>){
        observable.subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    fun getClimateData(observer: Observer<NetClimateBean>, location: String){
//        val encodeLocation = URLEncoder.encode(location, "utf-8")

        apiSubscribe(mApiService!!.getCategory(authorizationCode, format, location), observer)
    }
}