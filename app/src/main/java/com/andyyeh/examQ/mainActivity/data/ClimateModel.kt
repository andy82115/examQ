package com.andyyeh.examQ.mainActivity.data

import com.andyyeh.examQ.Configuration
import com.andyyeh.examQ.R

class ClimateModel {
    val datas = ArrayList<ClimateBean>()

    init {
        createTestData()
    }

    //FIXME: This is fake data for UI flow
    private fun createTestData(){
        val bean = ClimateBean(Configuration.CLIMATE_TXT_TYPE, "2020", "2020","20C", -1)
        val bean2 = ClimateBean(Configuration.CLIMATE_IMG_TYPE, "2020", "2020","20", R.mipmap.ic_launcher)

        datas.add(bean)
        datas.add(bean2)
    }
}