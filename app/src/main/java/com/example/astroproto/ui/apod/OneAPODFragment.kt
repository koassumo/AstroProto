package com.example.astroproto.ui.apod

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.astroproto.ApiHolder
import com.example.astroproto.R
import com.example.astroproto.model.retrofit.RetrofitRepoApi
import com.example.astroproto.model.entity.APODResponseDTO
import io.reactivex.rxjava3.core.Single
import kotlinx.android.synthetic.main.one_apod_fragment.*

class OneAPODFragment : Fragment() {

    companion object {
        fun newInstance() = OneAPODFragment()
    }

    //private lateinit var viewModel: OneAPODViewModel
    //private lateinit var apodResponseBundle: APODResponseTEMP

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.one_apod_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(this).get(OneAPODViewModel::class.java)
//        apodResponseBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: APODResponseTEMP()
        // mainView.visibility = View.GONE
        // loadingLayout.visibility = View.VISIBLE

// *************
        val anySingle: Single<APODResponseDTO> = RetrofitRepoApi(ApiHolder().dataApi).getRepoTodayApi()
        anySingle.subscribe({
            println(it)
            displayData(it)
        }, {
            println("onError: ${it.message}-----------------------------------------------")
        })
    }

    private fun displayData(apodResponseDTO: APODResponseDTO) {
        tv_title_apod.text = apodResponseDTO.title
        tv_date_apod.text = apodResponseDTO.date
        tv_copyright_apod.text = "\u00A9 ${apodResponseDTO.copyright}"
        tv_explanation_apod.text = apodResponseDTO.explanation
        //iv_url_apod.setImageResource(R.drawable.apod_temp)
    }

}



