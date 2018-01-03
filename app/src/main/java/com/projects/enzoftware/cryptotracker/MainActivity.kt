package com.projects.enzoftware.cryptotracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.projects.enzoftware.cryptotracker.adapter.CryptoCoinAdapter


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidNetworking.initialize(applicationContext)
        AndroidNetworking .get("https://api.coinmarketcap.com/v1/ticker/")
                .setPriority(Priority.LOW)
                .addQueryParameter("limit","10")
                .build()
                .getAsObjectList(CryptoCoin::class.java , object : ParsedRequestListener<List<CryptoCoin>>{
                    override fun onResponse(response: List<CryptoCoin>?) {
                        if (response != null) {
                            for (item in response) {
                                Log.i("success", item.id)
                                Log.i("success", item.name)
                                Log.i("success", item.symbol)
                                Log.i("success", item.price_usd)
                                Log.i("success", item.price_btc)
                                Log.i("success", item.rank)
                            }
                            printCryptoCoins(response)
                        }
                    }

                    override fun onError(anError: ANError?) {
                        Log.e("errorcillo",anError.toString())
                    }

                })
    }


    fun printCryptoCoins(list : List<CryptoCoin>?){
        val recycler = findViewById<RecyclerView>(R.id.recyclerViewCoins)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.hasFixedSize()
        recycler.adapter = CryptoCoinAdapter(this,list)
    }
}



