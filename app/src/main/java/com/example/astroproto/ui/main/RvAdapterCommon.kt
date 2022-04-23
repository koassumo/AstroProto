package com.example.astroproto.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astroproto.R
import com.example.astroproto.ui.IMyOnClickListener
import kotlinx.android.synthetic.main.item_rv_main_common.view.*

class RvAdapterCommon : RecyclerView.Adapter<RvAdapterCommon.ViewHolder> () {

    var adapterList: List<ItemRvCommon> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var myListener: IMyOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_main_common, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(adapterList[position])
    }

    override fun getItemCount(): Int = adapterList.size


    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {
        fun bind (adapterItemView: ItemRvCommon) {
            itemView.findViewById<TextView>(R.id.tv_title).text = adapterItemView.title

            when (adapterItemView.imageName) {
                "rv_apod_today" -> itemView.iv_pic.setImageResource(R.drawable.rv_apod_today)
                "rv_apod_before" -> itemView.iv_pic.setImageResource(R.drawable.rv_pod_before)

                "rv_solar_today" -> itemView.iv_pic.setImageResource(R.drawable.rv_solar_today)
                "rv_solar_before" -> itemView.iv_pic.setImageResource(R.drawable.rv_solar_before)
                "rv_solar_forecast" -> itemView.iv_pic.setImageResource(R.drawable.rv_solar_forecast)

                "rv_geo_today" -> itemView.iv_pic.setImageResource(R.drawable.rv_geo_today)
                "rv_geo_before" -> itemView.iv_pic.setImageResource(R.drawable.rv_geo_before)
                "rv_geo_forecast" -> itemView.iv_pic.setImageResource(R.drawable.rv_geo_forecast)

                "rv_epic_today" -> itemView.iv_pic.setImageResource(R.drawable.rv_epic_today)
                "rv_epic_before" -> itemView.iv_pic.setImageResource(R.drawable.rv_epic_before)

                "rv_mars_curiosity" -> itemView.iv_pic.setImageResource(R.drawable.rv_mars_curiosity)
                "rv_mars_spirit" -> itemView.iv_pic.setImageResource(R.drawable.rv_mars_spirit)
                "rv_mars_opportunity" -> itemView.iv_pic.setImageResource(R.drawable.rv_mars_opportunity)
            }
            //itemView.findViewById<ImageView>(R.id.iv_pic).setImageResource(R.drawable.no_image)


            itemView.setOnClickListener {
                myListener?.onMyClicked (view = it)
                // это callback, а сам метод onMyClicked прописан по-разному в каждом подписчике
                // (в View, во фрагменте
            }

        }
    }



}