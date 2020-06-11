package com.andyyeh.examQ

import android.util.Log
import com.andyyeh.examQ.mainActivity.data.ClimateBean
import com.example.andyutils.designPatternUtils.SingletonHolder
import com.example.climateapi.NetClimateBean

class BeanTransformer private constructor(){

    companion object {
        val instance = BeanTransformer()
    }

    /**transform complicated online bean to the local bean
     * @param netClimateBean is the online data bean for with Gson format
     * @return the data for local bean
     * **/
    fun netClimate2Climate(netClimateBean: NetClimateBean): ArrayList<ClimateBean>{
        /**only got Taipei city**/
        val datas = netClimateBean.records.location[0].weatherElement[2].time
        Log.e("data", "$datas")
        val climateBeans = ArrayList<ClimateBean>()
        for (data in datas){
            val parameter = data.parameter.parameterName + data.parameter.parameterUnit
            val climateBean = ClimateBean(Configuration.CLIMATE_TXT_TYPE,
            data.startTime,
            data.endTime,
            parameter,
            -1)

            climateBeans.add(climateBean)
        }
        return climateBeans
    }
}