package com.example.astroproto.ui.apod

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astroproto.R
import com.example.astroproto.model.IImageLoader
import com.example.astroproto.model.entity.APODResponseDTO
import com.example.astroproto.ui.GladeImageLoader

import com.example.astroproto.ui.IMyOnClickListener
import kotlinx.android.synthetic.main.one_apod_fragment.*
import kotlinx.android.synthetic.main.one_apod_fragment_v_constrained.view.*

class RvAdapterVertical (val imageLoader: IImageLoader<ImageView>):
    RecyclerView.Adapter<RvAdapterVertical.ViewHolder>() {

    var adapterList: List<APODResponseDTO> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var myListener: IMyOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_apod, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(adapterList[position])
    }

    override fun getItemCount(): Int = adapterList.size


    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {
        fun bind (adapterItemView: APODResponseDTO) {
            itemView.findViewById<TextView>(R.id.tv_title_apod).text = adapterItemView.title
            itemView.findViewById<TextView>(R.id.tv_date_apod).text = adapterItemView.date
            if (adapterItemView.copyright != null)
                itemView.tv_copyright_apod.text = "\u00A9 ${adapterItemView.copyright}"
            else
                itemView.tv_copyright_apod.text = ""
            adapterItemView.url?.let { imageLoader.loadInto(it, itemView.iv_url_apod) }
//            itemView.findViewById<TextView>(R.id.tv_copyright_apod).text = "\u00A9 ${adapterItemView.copyright}"
//            itemView.iv_url_apod.setImageResource(R.drawable.apod_temp)
//
//            when (adapterItemView.title) {
//                "The Gator-back Rocks of Mars" -> itemView.iv_url_apod.setImageResource(R.drawable.apod_temp2)
//            }
            //itemView.findViewById<ImageView>(R.id.iv_pic).setImageResource(R.drawable.no_image)


            itemView.setOnClickListener {
                myListener?.onMyClicked(it)
            }

        }
    }



}