package com.example.astroproto.ui.apod

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.example.astroproto.ApiHolder
import com.example.astroproto.R
import com.example.astroproto.model.entity.APODResponseDTO
import com.example.astroproto.model.retrofit.RetrofitRepoApi
import com.example.astroproto.ui.GladeImageLoader
import io.reactivex.rxjava3.core.Single
import kotlinx.android.synthetic.main.one_apod_fragment.*
import kotlinx.android.synthetic.main.one_apod_fragment.iv_url_apod
import kotlinx.android.synthetic.main.one_apod_fragment.tv_copyright_apod
import kotlinx.android.synthetic.main.one_apod_fragment.tv_date_apod
import kotlinx.android.synthetic.main.one_apod_fragment.tv_explanation_apod
import kotlinx.android.synthetic.main.one_apod_fragment.tv_title_apod
import kotlinx.android.synthetic.main.one_apod_fragment_v_constrained.*
import kotlinx.android.synthetic.main.one_apod_fragment_v_constrained.view.*


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
        val anySingle: Single<APODResponseDTO> =
            RetrofitRepoApi(ApiHolder().dataApi).getRepoOneDayApi()
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

        when (apodResponseDTO.media_type) {
            "image" -> {
                val imageLoader = GladeImageLoader()
                apodResponseDTO.url?.let { imageLoader.loadInto(it, iv_url_apod) }
            }
            "video" -> {
//                wv_url_video_apod.getSettings().setJavaScriptEnabled(true)
//                wv_url_video_apod.getSettings().setPluginState(PluginState.ON)
//                wv_url_video_apod.loadUrl("https://www.youtube.com/embed/m8qvOpcDt1o?rel=0" + "?autoplay=1&vq=small")
//                wv_url_video_apod.setWebChromeClient(WebChromeClient())

//                val videoView = wv_url_video_apod
//                val mediaController = MediaController(parentFragment?.context)
//                mediaController.setAnchorView(videoView)
//                val uri: Uri = Uri.parse("https://www.youtube.com/embed/m8qvOpcDt1o?rel=0")
//                videoView.setMediaController(mediaController)
//                videoView.setVideoURI(uri)
//                videoView.requestFocus()
//
//                videoView.start()

            }

        }

    }
}



