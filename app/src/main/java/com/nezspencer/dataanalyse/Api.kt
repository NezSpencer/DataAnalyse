package com.nezspencer.dataanalyse

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by reach1 on 2/17/18.
 */
interface Api {

    //todo create an interface method with your api endpoint here (Retrofit)
    @GET("fetchwords/mostused")
    fun fetchWords(@Query("num") num: Int): Single<Response<Void>>
}