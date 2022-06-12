package com.example.astroproto.ui.main

import android.animation.ValueAnimator
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.astroproto.R
import com.example.astroproto.databinding.MainFragmentBinding
import com.example.astroproto.ui.BaseFragment
import com.example.astroproto.ui.IMyOnClickListener
import com.example.astroproto.ui.apod.ListAPODFragment
import com.example.astroproto.ui.solar.ListSolarFragment

// more comment

class MainFragment : BaseFragment<MainFragmentBinding>(MainFragmentBinding::inflate) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    // backdrop
    var showBackLayout = false
    var frontLayoutParams: RelativeLayout.LayoutParams? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return providePersistentView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        // 1. RV - A Picture Of The Day (APOD)
        binding.rvApod.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val adapterAPOD = RvAdapterCommon()
        binding.rvApod.adapter = adapterAPOD

        viewModel.liveDataAPOD.observe(viewLifecycleOwner, Observer {
            adapterAPOD.adapterList = it       // вызов set в адаптере
        })

        adapterAPOD.myListener = object: IMyOnClickListener {
            override fun onMyClicked(rv_item_view: View) {
                // title_apod.text = rv_item_view.findViewById<TextView>(R.id.tv_title).text as String
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, ListAPODFragment())?.addToBackStack(null)
                    ?.commit()
            }
        }


        // 2. RV - Solar Flare
        binding.rvSolar.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapterSolar = RvAdapterCommon()
        binding.rvSolar.adapter = adapterSolar

        viewModel.liveDataSolar.observe(viewLifecycleOwner, Observer {
            adapterSolar.adapterList = it       // вызов set в адаптере
        })
        adapterSolar.myListener = object: IMyOnClickListener {
            override fun onMyClicked(rv_item_view: View) {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, ListSolarFragment())?.addToBackStack(null)
                    ?.commit()
            }
        }


        // 3. RV - Geomagnetic Storm
        binding.rvGeo.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapterGeo = RvAdapterCommon()
        binding.rvGeo.adapter = adapterGeo

        viewModel.liveDataGeo.observe(viewLifecycleOwner, Observer {
            adapterGeo.adapterList = it       // вызов set в адаптере
        })
        // TODO: onClickListener

        // 4. RV - Earth Polychromatic Imaging Camera (Epic)
        binding.rvEpic.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapterEpic = RvAdapterCommon()
        binding.rvEpic.adapter = adapterEpic

        viewModel.liveDataEpic.observe(viewLifecycleOwner, Observer {
            adapterEpic.adapterList = it       // вызов set в адаптере
        })
        // TODO: onClickListener


        // 5. RV - Mars Rovers Photos
        binding.rvMars.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapterMars = RvAdapterCommon()
        binding.rvMars.adapter = adapterMars

        viewModel.liveDataMars.observe(viewLifecycleOwner, Observer {
            adapterMars.adapterList = it       // вызов set в адаптере
        })
        // TODO: onClickListener


        binding.appBarLayout.findViewById<View>(R.id.main_appbar).findViewById<View>(R.id.settings).setOnClickListener { dropLayout() }
        binding.appBarLayout.findViewById<View>(R.id.main_appbar).setOnClickListener { dropLayout() }

    }


    private fun dropLayout() {
        showBackLayout = !showBackLayout
        frontLayoutParams = binding.frontLayoutMain.layoutParams as RelativeLayout.LayoutParams
        if (showBackLayout) {
            val varl = ValueAnimator.ofInt(binding.backLayoutMain.height)
            varl.duration = 200
            varl.addUpdateListener { animation ->
                frontLayoutParams!!.setMargins(0, (animation.animatedValue as Int) - 72, 0, 0)
                binding.frontLayoutMain.layoutParams = frontLayoutParams
            }
            varl.start()
        } else {
            frontLayoutParams!!.setMargins(0, 18, 0, 0)
            binding.frontLayoutMain.layoutParams = frontLayoutParams
            val anim = TranslateAnimation(
                0F, 0F,
                binding.backLayoutMain.height.toFloat(), 0F
            )
            anim.startOffset = 0
            anim.duration = 200
            binding.frontLayoutMain.startAnimation(anim)
        }
    }


    private fun onApodClickListener() {
        findNavController().navigate(R.id.action_mainFragment2_to_listAPODFragment)
    }

    private fun onSolarFlareClickListener(itemRv: ItemRv) {
        if (itemRv.title == getString(R.string.title_main_solar))
            findNavController().navigate(R.id.action_main_fragment_to_navigation_flr)
        else
            findNavController().navigate(R.id.action_main_fragment_to_navigation_gst)
    }

    private fun onEpicClickListener(itemRv: ItemRv) {
        findNavController().navigate(R.id.action_main_fragment_to_navigation_epic)
    }

    private fun onMarsClickListener(itemRv: ItemRv) {
        val action = MainFragmentDirections.actionMainFragmentToNavigationMrp(itemRv.title)
        findNavController().navigate(action)
    }



}