package com.andyyeh.examQ.mainActivity

import com.andyyeh.examQ.mainActivity.data.ClimateBean
import com.example.climateapi.NetClimateBean
import io.reactivex.functions.Consumer

/**The contract to help unit test**/
interface MainContract {
    interface Repository {
        fun getDataFromInternet(location: String,  consumer: Consumer<ArrayList<ClimateBean>>)
    }
}