package com.example.dataloadingkt.activities

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.annotation.IntegerRes
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.RetryPolicy
import com.android.volley.toolbox.JsonObjectRequest
import com.example.dataloadingkt.R
import com.example.dataloadingkt.app.AppController
import com.example.dataloadingkt.models.AssetInfoModel
import com.example.dataloadingkt.utils.DataManager
import org.json.JSONObject
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    var DataList: ArrayList<AssetInfoModel> = ArrayList()
    var tvText: TextView? = null
    var handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppController.mainActivity = this

        FetchAsynTask.execute()
    }

    object FetchAsynTask : AsyncTask<Void, Void, Void>() {
        var params: JSONObject = JSONObject()
        var dataList: ArrayList<AssetInfoModel> = ArrayList()

        override fun doInBackground(vararg p0: Void?): Void? {

            Log.i("params", params.toString())

            val jsonReq: JsonObjectRequest =
                JsonObjectRequest(
                    Request.Method.POST,
                    "http://192.168.1.122/mobile_app/public/application/assetusage/usagelist",
                    params,
                    Response.Listener { response ->

                        if (response != null) {
                            try {

                                if (response.getJSONArray("CostCentreList").length() != 0) {

                                    var jadataList = response.getJSONArray("CostCentreList")

                                    for (i in 0 until jadataList.length()) {
                                        val jodataList = jadataList.getJSONObject(i) as JSONObject
                                        var model: AssetInfoModel = AssetInfoModel()
                                        model.costCenterId = StringBuilder(jodataList.getString("CostCentreId"))
                                        model.costCenterName = StringBuilder(jodataList.getString("CostCentreName"))
                                        dataList.add(model)
                                    }
                                }

                                AppController.mainActivity?.displayData(dataList)

                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }

                    },
                    Response.ErrorListener {

                        Toast.makeText(AppController.context, "Failed to Load!", Toast.LENGTH_SHORT).show()
                    })

            val retryPolicy: RetryPolicy =
                DefaultRetryPolicy(
                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 5,
                    0,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
            jsonReq.retryPolicy = retryPolicy

            AppController.instance?.addToRequestQueue(jsonReq)

            return null
        }

        override fun onPreExecute() {
//            super.onPreExecute()
            try {
                params.put("ClientId", "10017")
                params.put("UserId", "1")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun onPostExecute(result: Void?) {
//            super.onPostExecute(result)
        }

    }


    fun displayData(arrList: ArrayList<AssetInfoModel>): Unit {

        Toast.makeText(this@MainActivity, "" + arrList.size.toString(), Toast.LENGTH_SHORT).show()

    }


}
