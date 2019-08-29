package com.example.dataloadingkt.models

import android.os.Parcel
import android.os.Parcelable
import java.lang.StringBuilder

data class AssetInfoModel(var temp: String = "null") : Parcelable {

    internal var costCenterId: StringBuilder? = null
    internal var costCenterName: StringBuilder? = null

    constructor(parcel: Parcel) : this() {
        this.costCenterId = StringBuilder(parcel.readString())
        this.costCenterName = StringBuilder(parcel.readString())
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(costCenterId.toString())
        parcel.writeString(costCenterName.toString())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AssetInfoModel> {
        override fun createFromParcel(parcel: Parcel): AssetInfoModel {
            return AssetInfoModel(parcel)
        }

        override fun newArray(size: Int): Array<AssetInfoModel?> {
            return arrayOfNulls(size)
        }
    }

}