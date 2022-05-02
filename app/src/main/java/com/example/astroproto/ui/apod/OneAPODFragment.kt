package com.example.astroproto.ui.apod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.widget.ImageView
import androidx.fragment.app.Fragment
//import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.astroproto.R
import com.example.astroproto.model.entity.APODResponseDTO
import com.example.astroproto.ui.GladeImageLoader
import kotlinx.android.synthetic.main.one_apod_fragment_start.*
//import kotlinx.android.synthetic.main.one_apod_fragment_v_constrained.*
//import kotlinx.android.synthetic.main.one_apod_fragment_v_constrained.tv_explanation_apod_v


class OneAPODFragment : Fragment() {

    companion object {

        //константа для передачи данных из фрагмента во фрагмент
        const val APOD_RESPONSE_DTO_EXTRA = "APOD_RESPONSE_DTO_EXTRA"

        // фабричный метод
        fun newInstance(bundle: Bundle): OneAPODFragment =
            OneAPODFragment().apply { arguments = bundle }
    }


    //private lateinit var viewModel: OneAPODViewModel
    //private lateinit var apodResponseBundle: APODResponseTEMP
    private var isExpanded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.one_apod_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // т.к. данные передаем из фрагмента во фрагмент (bundle), то данные является parcelable
        // достаем их в нормальный вид
        val myAPODResponseDTO = arguments?.getParcelable<APODResponseDTO>(APOD_RESPONSE_DTO_EXTRA)


//        viewModel = ViewModelProvider(this).get(OneAPODViewModel::class.java)
//        apodResponseBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: APODResponseTEMP()
        // mainView.visibility = View.GONE
        // loadingLayout.visibility = View.VISIBLE

// ************* ВАРИАНТ 1. Выводим на дисплей данные, которые пришли из предыдущего фрагмента
//                          т.е не идем в Инет, чтобы обновить данные
        if (myAPODResponseDTO != null) {
            displayData(myAPODResponseDTO)
        }


// ************* ВАРИАНТ 2. Идем в инет для обновления данных и выводим их на дисплей
//
//        val anySingle: Single<APODResponseDTO> =
//            RetrofitRepoApi(ApiHolder().dataApi).getRepoOneDayApi()
//        anySingle.subscribe({
//            println(it)
//            displayData(it)
//        }, {
//            println("onError: ${it.message}-----------------------------------------------")
//        })



    }

    private fun displayData(apodResponseDTO: APODResponseDTO) {
//        tv_title_apod.text = apodResponseDTO.title
//        tv_date_apod.text = apodResponseDTO.date
//        tv_copyright_apod.text = "\u00A9 ${apodResponseDTO.copyright}"
//        tv_explanation_apod_v.text = apodResponseDTO.explanation

        if (apodResponseDTO.media_type == "video") {
            wv_one_url_video_apod.visibility = View.VISIBLE
            iv_url_apod_v.visibility = View.GONE
            val mWebView = wv_one_url_video_apod
            mWebView.getSettings().setJavaScriptEnabled(true)
            mWebView.getSettings().setPluginState(WebSettings.PluginState.ON)
            mWebView.loadUrl(apodResponseDTO.url + "&autoplay=1&vq=small")
            mWebView.setWebChromeClient(WebChromeClient())
        } else {
//            wv_one_url_video_apod.visibility = View.GONE
//            iv_url_apod_v.visibility = View.VISIBLE
            val imageLoader = GladeImageLoader()
            apodResponseDTO.url?.let { imageLoader.loadInto(it, iv_url_apod_v as ImageView) }
        }


    }
}



