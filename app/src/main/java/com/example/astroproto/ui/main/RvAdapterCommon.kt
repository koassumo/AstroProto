package com.example.astroproto.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.astroproto.R
import com.example.astroproto.databinding.ItemRvMainCommonBinding
import com.example.astroproto.ui.IMyOnClickListener

class RvAdapterCommon : RecyclerView.Adapter<RvAdapterCommon.ViewHolder> () {

    var adapterList: List<ItemRvCommon> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var myListener: IMyOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(ItemRvMainCommonBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(adapterList[position])
    }

    override fun getItemCount(): Int = adapterList.size


    inner class ViewHolder (private val itemViewBinding: ItemRvMainCommonBinding) : RecyclerView.ViewHolder (itemViewBinding.root) {
        fun bind (adapterItemView: ItemRvCommon) {
            itemViewBinding.tvTitle.text = adapterItemView.title

            when (adapterItemView.imageName) {
                "rv_apod_today" -> itemViewBinding.ivPic.setImageResource(R.drawable.rv_apod_today)
                "rv_apod_before" -> itemViewBinding.ivPic.setImageResource(R.drawable.rv_pod_before)

                "rv_solar_today" -> itemViewBinding.ivPic.setImageResource(R.drawable.rv_solar_today)
                "rv_solar_before" -> itemViewBinding.ivPic.setImageResource(R.drawable.rv_solar_before)
                "rv_solar_forecast" -> itemViewBinding.ivPic.setImageResource(R.drawable.rv_solar_forecast)

                "rv_geo_today" -> itemViewBinding.ivPic.setImageResource(R.drawable.rv_geo_today)
                "rv_geo_before" -> itemViewBinding.ivPic.setImageResource(R.drawable.rv_geo_before)
                "rv_geo_forecast" -> itemViewBinding.ivPic.setImageResource(R.drawable.rv_geo_forecast)

                "rv_epic_today" -> itemViewBinding.ivPic.setImageResource(R.drawable.rv_epic_today)
                "rv_epic_before" -> itemViewBinding.ivPic.setImageResource(R.drawable.rv_epic_before)

                "rv_mars_curiosity" -> itemViewBinding.ivPic.setImageResource(R.drawable.rv_mars_curiosity)
                "rv_mars_spirit" -> itemViewBinding.ivPic.setImageResource(R.drawable.rv_mars_spirit)
                "rv_mars_opportunity" -> itemViewBinding.ivPic.setImageResource(R.drawable.rv_mars_opportunity)
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