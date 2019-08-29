package com.example.dataloadingkt.utils

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.RetryPolicy
import com.android.volley.toolbox.JsonObjectRequest
import com.example.dataloadingkt.app.AppController
import org.json.JSONObject

class DataManager(mContext: AppController? = AppController.instance) {

    //Static Methods & Variable
    companion object {
        var mInstance: DataManager = DataManager()

        /*Global API */
        private val GETDATA_URL: String = "http://192.168.1.11/mobile_app/public/application/assetusage/usagelist"
    }


    fun getDataLoad(): Unit {

        var params: JSONObject = JSONObject()
        try {

            params.put("ClientId", "10017")
            params.put("UserId", "1")

        } catch (e: Exception) {
            e.printStackTrace()
        }


        val jsonReq: JsonObjectRequest = JsonObjectRequest(Request.Method.POST, GETDATA_URL, params, Response.Listener { response ->


                if (response != null) {
                    try {


                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }


            }, Response.ErrorListener {

                Toast.makeText(AppController.context, "Failed to Load!", Toast.LENGTH_SHORT).show()
            })

        val retryPolicy: RetryPolicy =
            DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 5, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        jsonReq.retryPolicy = retryPolicy
        // Adding request to volley request queue
        AppController.instance?.addToRequestQueue(jsonReq, "")

    }


}