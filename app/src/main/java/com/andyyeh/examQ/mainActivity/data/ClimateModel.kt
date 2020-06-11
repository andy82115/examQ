package com.andyyeh.examQ.mainActivity.data

import com.andyyeh.examQ.Configuration
import com.andyyeh.examQ.R

class ClimateModel {
    var datas = ArrayList<ClimateBean>()

    init {}

    fun addData(bean: ClimateBean){
        datas.add(bean)
        datas.add(ClimateBean(Configuration.CLIMATE_IMG_TYPE, "", "", "", R.mipmap.ic_launcher_round))
    }
}