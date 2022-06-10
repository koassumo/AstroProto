package com.example.astroproto.ui.apod

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.astroproto.R
import com.example.astroproto.databinding.ListApodFragmentBinding
import com.example.astroproto.model.entity.*
import com.example.astroproto.ui.IMyOnClickListenerAPOD


class ListAPODFragment : Fragment() {

    companion object {
        fun newInstance() = ListAPODFragment()
    }

    private lateinit var viewModel: ListAPODViewModel

    private var _binding: ListApodFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_apod_fragment, container, false)
        _binding = ListApodFragmentBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListAPODViewModel::class.java)


        binding.rvListApodVertical.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapterAPODVertical = RvAdapterVertical()
        binding.rvListApodVertical.adapter = adapterAPODVertical


        viewModel.liveDataAPODVertical.observe(viewLifecycleOwner, Observer {
            adapterAPODVertical.adapterList = it
        })


//        val anySingle: Single<List<APODResponseDTO>> =
//            RetrofitRepoApi(ApiHolder().dataApi).getRepoListApi()
//        anySingle.subscribe({
//            adapterAPODVertical.adapterList = it
//            println("$it---------------------------===1===1====1========--------------------")
//            println("--------------------------------====1===1===1========--------------------")
//        }, {
//            println("onError: ${it.message}-----------err-------------------*----*-----------")
//        })





        adapterAPODVertical.myListenerAPOD = object : IMyOnClickListenerAPOD {
            override fun onMyClicked(apodResponseDTO: APODResponseDTO) {
                activity?.supportFragmentManager?.let {
                    it.beginTransaction()
                        .replace(R.id.container, OneAPODFragment.newInstance(Bundle().apply {
                            putParcelable(OneAPODFragment.APOD_RESPONSE_DTO_EXTRA, apodResponseDTO)
                        }
                        ))
                        .addToBackStack(null)
                        .commit()
                }
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


