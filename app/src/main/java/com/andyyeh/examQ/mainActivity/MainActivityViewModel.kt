package com.andyyeh.examQ.mainActivity

import androidx.databinding.ObservableField
import com.andyyeh.examQ.Configuration
import com.andyyeh.examQ.mainActivity.data.ClimateModel
import com.andyyeh.examQ.mvvmBase.BaseViewModel
import io.reactivex.Completable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

class MainActivityViewModel(mainActivityRepository: MainActivityRepository, climateModel: ClimateModel) : BaseViewModel() {
    private val TAG = MainActivityViewModel::class.java.simpleName
    private val repository = mainActivityRepository
    private var model = climateModel

    /**simply request the final result from internet
     * @param action is the observer for update the adapter
     * Completable will subscribe the action with no parameter
     * the purpose for the Completable is to notify the MainActivity
     * **/
    fun requestClimateData(action: Action){
        repository.getDataFromInternet("臺北市", Consumer {
            for (item in it){
                model.addData(item)
            }
            Completable.fromAction(action).subscribe()
        })
    }
}