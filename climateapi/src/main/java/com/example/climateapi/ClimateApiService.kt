package com.example.andynovelapi.internetApi

import com.example.climateapi.NetClimateBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ClimateApiService {
    @GET("F-C0032-001")
    fun getCategory(@Query("Authorization") authorization: String,
                    @Query("format") format: String,
                    @Query("locationName") locationName: String): Observable<NetClimateBean>
}