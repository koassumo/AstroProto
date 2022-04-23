package com.example.astroproto.ui.solar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.astroproto.ApiHolder
import com.example.astroproto.R
import com.example.astroproto.model.entity.APODResponseDTO
import com.example.astroproto.model.entity.SolarResponseDTO
import com.example.astroproto.model.retrofit.RetrofitRepoApi
import io.reactivex.rxjava3.core.Single
import kotlinx.android.synthetic.main.list_solar_fragment.*

class ListSolarFragment : Fragment() {

    companion object {
        fun newInstance() = ListSolarFragment()
    }

    private lateinit var viewModel: ListSolarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_solar_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListSolarViewModel::class.java)



        rv_list_solar_vertical.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapterSolarVertical = RvAdapterSolarVertical()
        rv_list_solar_vertical.adapter = adapterSolarVertical


//        val anySingle: Single<List<SolarResponseDTO>> = RetrofitRepoApi(ApiHolder().dataApi).getRepoListSolarApi()
//        anySingle.subscribe({
//            println("$it-----------------==================--------------------")
//            println("-----------------==================--------------------")
//            println("-----------------==================--------------------")
//            adapterSolarVertical.adapterList = it
//
//        }, {
//            println("onError: ${it.message}-----------err--------err-------------*----*-----------")
//        })
//




        viewModel.liveDataSolarVertical.observe(viewLifecycleOwner, Observer {
            println("$it-----------------=========*=========--------------------")
            println("-----------------======**********=========--------------------")
            println("-----------------=========***=========--------------------")
            adapterSolarVertical.adapterList = it       // вызов set в адаптере

        })



//        adapterAPODVertical.myListener = object : MyOnClickListener {
//            override fun onMyClicked(rv_item_view: View) {
//                activity?.supportFragmentManager?.beginTransaction()
//                    ?.replace(R.id.container, OneAPODFragment())?.addToBackStack(null)
//                    ?.commit()
//            }
//        }


    }



}