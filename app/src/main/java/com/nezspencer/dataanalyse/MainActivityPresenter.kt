package com.nezspencer.dataanalyse

import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by reach1 on 2/17/18.
 */
class MainActivityPresenter(contract: MainActivityContract, baseUrl: String) {
    val  url = baseUrl
    val mainContract = contract

    private fun prepareRetrofit(): Retrofit = Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    fun fetchWordList(){
        mainContract.loading()
        prepareRetrofit().create(Api::class.java)
                .fetchWords(5)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: DisposableSingleObserver<Response<Void>>(){
                    override fun onSuccess(t: Response<Void>) {
                        mainContract.hideLoading()
                        //todo pass the hashmap to mainCOntract.onSuccess()
                    }


                    override fun onError(e: Throwable) {
                        mainContract.hideLoading()
                        mainContract.onError("error occurred")
                    }
                })

    }

}