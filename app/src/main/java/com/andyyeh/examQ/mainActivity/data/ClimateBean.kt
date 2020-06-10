package com.andyyeh.examQ.mainActivity.data

import androidx.annotation.DrawableRes

data class ClimateBean(var Type: Int,
                       var StartTime: String,
                       var EndTime: String,
                       var Parameter: String,
                       @DrawableRes var ImgId: Int)

