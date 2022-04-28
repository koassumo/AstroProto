package com.example.astroproto.ui.solar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.astroproto.R
import com.example.astroproto.model.entity.SolarResponseDTO
import com.example.astroproto.ui.IMyOnClickListener
import kotlinx.android.synthetic.main.item_rv_solar.view.*

// в этой строке заненен конкретный из этого класса viewHolder на общий из библиотеки
class RvAdapterSolarVertical : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_SMALL = 1
    }

    var adapterList: List<SolarResponseDTO> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    //var myListener: IMyOnClickListener? = null

    // в этой строке заненен конкретный из этого класса viewHolder на общий из библиотеки
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        val myInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER -> HeadersViewHolder(myInflater.inflate(R.layout.item_rv_solar_header, parent, false))
            else -> SmallViewHolder(myInflater.inflate(R.layout.item_rv_solar, parent, false))
        }
    }

    // в этой строке заненен конкретный из этого класса viewHolder на общий из библиотеки
    // override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_HEADER -> {
                holder as HeadersViewHolder
                holder.bind(adapterList[position])
            }
            TYPE_SMALL -> {
                holder as SmallViewHolder
                holder.bind(adapterList[position])
            }
        }
    }

    override fun getItemCount(): Int = adapterList.size



    inner class HeadersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(adapterItemData: SolarResponseDTO) {
            itemView.tv_date_solar.text = adapterItemData.beginTime
        }
    }

    inner class SmallViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(adapterItemData: SolarResponseDTO) {
            itemView.tv_date_solar.text = adapterItemData.beginTime
            itemView.tv_solar_flare_class.text = adapterItemData.classType

            val cType = adapterItemData.classType?.take(1)
            itemView.view_scale_1of4.isVisible = true
            if (cType == "C" || cType == "M" || cType == "X") itemView.view_scale_2of4.isVisible = true
            if (cType == "M" || cType == "X") itemView.view_scale_3of4.isVisible = true
            if (cType == "X") itemView.view_scale_4of4.isVisible = true
        }
    }

    // определяем тип конкретного Item на основе его полей и позиции
    override fun getItemViewType(position: Int): Int {
        return when (adapterList[position].classType) {
            "header" -> TYPE_HEADER
            else -> TYPE_SMALL
        }
    }


        //itemView.view_scale_1of4.setCardBackgroundColor(Color.parseColor("#558b2f"))

//        itemView.setOnClickListener
//        {
//            myListener?.onMyClicked(it)
//        }


}

