package com.example.astroproto.ui.apod

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.astroproto.R
import com.example.astroproto.databinding.ItemRvApodBinding
import com.example.astroproto.model.entity.APODResponseDTO
import com.example.astroproto.ui.IMyOnClickListenerAPOD


class RvAdapterVertical() :
    RecyclerView.Adapter<RvAdapterVertical.ViewHolder>() {

    var adapterList: List<APODResponseDTO> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var myListenerAPOD: IMyOnClickListenerAPOD? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemRvApodBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(adapterList[position])
    }

    override fun getItemCount(): Int = adapterList.size


    inner class ViewHolder(private val itemViewBinding: ItemRvApodBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(adapterItemView: APODResponseDTO) {
            itemView.findViewById<TextView>(R.id.tv_title_apod).text = adapterItemView.title
            itemView.findViewById<TextView>(R.id.tv_date_apod).text = adapterItemView.date
            if (adapterItemView.copyright != null)
                itemViewBinding.tvCopyrightApod.text = "\u00A9 ${adapterItemView.copyright}"
            else
                itemViewBinding.tvCopyrightApod.text = ""

            if (adapterItemView.media_type == "video") {
                itemViewBinding.ivRvUrlApod.visibility = View.GONE
                itemViewBinding.wvRvUrlVideoApod.visibility = View.VISIBLE
                itemViewBinding.wvRvUrlVideoApod.getSettings().setJavaScriptEnabled(true)
                itemViewBinding.wvRvUrlVideoApod.getSettings()
                    .setPluginState(WebSettings.PluginState.ON)
                itemViewBinding.wvRvUrlVideoApod.loadUrl(adapterItemView.url + "&autoplay=1&vq=small")
                itemViewBinding.wvRvUrlVideoApod.setWebChromeClient(WebChromeClient())
            } else {
                itemViewBinding.ivRvUrlApod.load(adapterItemView.url)

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


    fun updateList (newList: List<APODResponseDTO>) {
        this.adapterList = newList
    }


}