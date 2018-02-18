package com.nezspencer.dataanalyse

/**
 * Created by reach1 on 2/17/18.
 */
interface MainActivityContract {
    fun loading()
    fun hideLoading()
    fun onSuccess(map: HashMap<String,Int>)
    fun onError(error: String)
}