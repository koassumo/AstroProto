package com.example.astroproto.ui.apod

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.astroproto.R
import com.example.astroproto.model.IImageLoader
import com.example.astroproto.model.entity.APODResponseDTO
import com.example.astroproto.ui.GladeImageLoader

import com.example.astroproto.ui.IMyOnClickListener
import com.example.astroproto.ui.IMyOnClickListenerAPOD
import kotlinx.android.synthetic.main.item_rv_apod.view.*
import kotlinx.android.synthetic.main.one_apod_fragment.*
import kotlinx.android.synthetic.main.one_apod_fragment_v_constrained.view.*
import kotlinx.android.synthetic.main.one_apod_fragment_v_constrained.view.tv_copyright_apod

class RvAdapterVertical(val imageLoader: IImageLoader<ImageView>) :
    RecyclerView.Adapter<RvAdapterVertical.ViewHolder>() {

    var adapterList: List<APODResponseDTO> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var myListenerAPOD: IMyOnClickListenerAPOD? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_apod, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(adapterList[position])
    }

    override fun getItemCount(): Int = adapterList.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(adapterItemView: APODResponseDTO) {
            itemView.findViewById<TextView>(R.id.tv_title_apod).text = adapterItemView.title
            itemView.findViewById<TextView>(R.id.tv_date_apod).text = adapterItemView.date
            if (adapterItemView.copyright != null)
                itemView.tv_copyright_apod.text = "\u00A9 ${adapterItemView.copyright}"
            else
                itemView.tv_copyright_apod.text = ""

            if (adapterItemView.media_type == "video") {
                itemView.iv_rv_url_apod.visibility = View.GONE
                itemView.wv_rv_url_video_apod.visibility = View.VISIBLE
                itemView.wv_rv_url_video_apod.getSettings().setJavaScriptEnabled(true)
                itemView.wv_rv_url_video_apod.getSettings().setPluginState(WebSettings.PluginState.ON)
                itemView.wv_rv_url_video_apod.loadUrl(adapterItemView.url + "&autoplay=1&vq=small")
                itemView.wv_rv_url_video_apod.setWebChromeClient(WebChromeClient())
            } else {
                adapterItemView.url?.let { imageLoader.loadInto(it, itemView.iv_rv_url_apod) }
            }
//            itemView.findViewById<TextView>(R.id.tv_copyright_apod).text = "\u00A9 ${adapterItemView.copyright}"
//            itemView.iv_url_apod.setImageResource(R.drawable.apod_temp)
//
//            when (adapterItemView.title) {
//                "The Gator-back Rocks of Mars" -> itemView.iv_url_apod.setImageResource(R.drawable.apod_temp2)
//            }
            //itemView.findViewById<ImageView>(R.id.iv_pic).setImageResource(R.drawable.no_image)


            itemView.setOnClickListener {
                myListenerAPOD?.onMyClicked(apodResponseDTO = adapterItemView)
            }

        }
    }


}