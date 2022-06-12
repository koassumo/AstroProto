package com.example.astroproto.ui.solar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.astroproto.R
import com.example.astroproto.databinding.ListSolarFragmentBinding

class ListSolarFragment : Fragment() {

    companion object {
        fun newInstance() = ListSolarFragment()
    }

    private lateinit var viewModel: ListSolarViewModel

    private var _binding: ListSolarFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_solar_fragment, container, false)
        _binding = ListSolarFragmentBinding.bind(view)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListSolarViewModel::class.java)



        binding.rvListSolarVertical.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapterSolarVertical = RvAdapterSolarVertical()
        binding.rvListSolarVertical.adapter = adapterSolarVertical


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