package com.example.astroproto.ui.apod

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.astroproto.ApiHolder
import com.example.astroproto.ApiHolder3
import com.example.astroproto.R
import com.example.astroproto.model.entity.*
import com.example.astroproto.model.retrofit.RetrofitRepoApi
import com.example.astroproto.model.retrofit.RetrofitRepoApi3
import com.example.astroproto.ui.GladeImageLoader
import com.example.astroproto.ui.IMyOnClickListener
import io.reactivex.rxjava3.core.Single
import kotlinx.android.synthetic.main.list_apod_fragment.*

class ListAPODFragment : Fragment() {

    companion object {
        fun newInstance() = ListAPODFragment()
    }

    private lateinit var viewModel: ListAPODViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_apod_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListAPODViewModel::class.java)


        val imageLoader = GladeImageLoader()
        rv_list_apod_vertical.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapterAPODVertical = RvAdapterVertical(imageLoader)
        rv_list_apod_vertical.adapter = adapterAPODVertical

//        viewModel.liveDataAPODVertical.observe(viewLifecycleOwner, Observer {
//            adapterAPODVertical.adapterList = it       // вызов set в адаптере
//        })


        val anySingle: Single<List<APODResponseDTO>> =
            RetrofitRepoApi(ApiHolder().dataApi).getRepoListApi()
        anySingle.subscribe({
            adapterAPODVertical.adapterList = it
            println("$it---------------------------===1===1====1========--------------------")
            println("--------------------------------====1===1===1========--------------------")
        }, {
            println("onError: ${it.message}-----------err-------------------*----*-----------")
        })


        adapterAPODVertical.myListener = object : IMyOnClickListener {
            override fun onMyClicked(rv_item_view: View) {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, OneAPODFragment())?.addToBackStack(null)
                    ?.commit()
            }
        }
//
//        val anySingle3: Single<List<Response3>> = RetrofitRepoApi3(ApiHolder3().dataApi3).getRepo3()
//        anySingle3.subscribe({
//            println("$it-----------------==================--------------------")
//            println("-----------------====2===2==2========--------------------")
//            println("-----------------==================--------------------")
//        }, {
//            println("onError: ${it.message}-----------*--------*-------------*----*-----------")
//        })


    }
}


//


