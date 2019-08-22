package com.example.dataloadingkt.models

import java.io.Serializable
import java.lang.StringBuilder

class AssetInfoModel : Serializable {

    private var costCenterId: StringBuilder? = null
    private var costCenterName: StringBuilder? = null

    fun getClientName(): StringBuilder = this.costCenterName!!

    fun setClientName(costCenterName: StringBuilder): Unit {
        this.costCenterName = costCenterName
    }


    fun getClientId(): StringBuilder = this.costCenterId!!

    fun setClientId(costCenterId: StringBuilder): Unit {
        this.costCenterId = costCenterId
    }


}