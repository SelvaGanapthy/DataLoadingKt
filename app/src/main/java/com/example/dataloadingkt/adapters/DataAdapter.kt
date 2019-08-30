package com.example.dataloadingkt.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dataloadingkt.R
import com.example.dataloadingkt.activities.MainActivity
import com.example.dataloadingkt.models.AssetInfoModel
import org.w3c.dom.Text

class DataAdapter(var mainActivity: MainActivity, var dataList: ArrayList<AssetInfoModel>) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    var view: View? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.ViewHolder {
        view = LayoutInflater.from(mainActivity).inflate(R.layout.data_adapter, parent, false)
        return ViewHolder(view!!)
    }


    override fun onBindViewHolder(holder: DataAdapter.ViewHolder, position: Int) {
        var model: AssetInfoModel = dataList.get(position)
    }

    override fun getItemCount(): Int = dataList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvMName: TextView? = null
        var ivImage: ImageView? = null

        init {
            tvMName = itemView.findViewById<View>(R.id.tvMName) as TextView
            ivImage = itemView.findViewById<View>(R.id.ivImage) as ImageView
        }

    }

}