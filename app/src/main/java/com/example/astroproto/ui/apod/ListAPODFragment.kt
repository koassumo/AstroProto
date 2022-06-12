package com.example.astroproto.ui.apod

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
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
    private val adapterAPODVertical by lazy { RvAdapterVertical() }

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
        initRv()
        initViewModel()
        initListener()

    }

    private fun initRv () {
        binding.rvListApodVertical.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvListApodVertical.adapter = adapterAPODVertical
    }

    private fun initViewModel() {
        //option by callback (without coroutine)
        viewModel = ViewModelProvider(this).get(ListAPODViewModel::class.java)
        viewModel.liveDataAPODVertical.observe(viewLifecycleOwner, Observer {
            adapterAPODVertical.updateList(it)
        })
    }

    private fun initListener() {
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
    }

}

