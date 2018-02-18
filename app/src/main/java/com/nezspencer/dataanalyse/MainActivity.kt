package com.nezspencer.dataanalyse

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import net.alhazmy13.wordcloud.ColorTemplate
import net.alhazmy13.wordcloud.WordCloud

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityContract {


    lateinit var progress : ProgressDialog
    lateinit var resultMap: HashMap<String,Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //todo swap with your baseurl here. Note that baseUrl should end with a /
        val presenter = MainActivityPresenter(this,"http://enterbaseurl/")
        presenter.fetchWordList()
        resultMap = HashMap()
        resultMap.put("One",1)
        resultMap.put("Two",2)
        resultMap.put("Three",3)
        resultMap.put("Four",4)
        resultMap.put("Five",5)
        resultMap.put("Six",6)

        wcv_datacloud.setColors(ColorTemplate.MATERIAL_COLORS)
        processMap(resultMap)

    }

    override fun loading() {
        if (!::progress.isInitialized || !progress.isShowing)
            progress = ProgressDialog.show(this@MainActivity,"Loading",
                    "Please wait...",true,false)
    }

    override fun onSuccess(map: HashMap<String, Int>) {
        processMap(map)

    }

    override fun onError(error: String) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        if (progress?.isShowing)
            progress.dismiss()
    }

    fun processMap(map: HashMap<String,Int>){
        val list = ArrayList<WordCloud>()
        for ((key,value) in map){

            list.add(WordCloud(key,value))
        }

        wcv_datacloud.setDataSet(list)
        wcv_datacloud.notifyDataSetChanged()
    }
}
