package com.example.astroproto.ui.apod

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Toast
import androidx.fragment.app.Fragment
//import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.astroproto.ApiHolder
import com.example.astroproto.R
import com.example.astroproto.model.entity.APODResponseDTO
import com.example.astroproto.model.retrofit.RetrofitRepoApi
import com.example.astroproto.ui.GladeImageLoader
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import io.reactivex.rxjava3.core.Single
import kotlinx.android.synthetic.main.item_rv_apod.view.*
import kotlinx.android.synthetic.main.one_apod_fragment.*
import kotlinx.android.synthetic.main.one_apod_fragment.iv_url_apod_v
import kotlinx.android.synthetic.main.one_apod_fragment.tv_copyright_apod
import kotlinx.android.synthetic.main.one_apod_fragment.tv_date_apod
import kotlinx.android.synthetic.main.one_apod_fragment.tv_title_apod
import kotlinx.android.synthetic.main.one_apod_fragment_v_constrained.*
import kotlinx.android.synthetic.main.one_apod_fragment_v_constrained.tv_explanation_apod_v


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




//        тут херь
//        // Get reference to the view of Video player
//        val ytPlayer = view_youtube_player as YouTubePlayerView
//
//        ytPlayer.initialize(
//            api_key,
//            object : YouTubePlayer.OnInitializedListener {
//                // Implement two methods by clicking on red
//                // error bulb inside onInitializationSuccess
//                // method add the video link or the playlist
//                // link that you want to play In here we
//                // also handle the play and pause
//                // functionality
//                override fun onInitializationSuccess(
//                    provider: YouTubePlayer.Provider,
//                    youTubePlayer: YouTubePlayer, b: Boolean
//                ) {
//                    youTubePlayer.loadVideo("https://www.youtube.com/embed/m8qvOpcDt1o?rel=0")
//                    youTubePlayer.play()
//                }
//
//                // Inside onInitializationFailure
//                // implement the failure functionality
//                // Here we will show toast
//                override fun onInitializationFailure(
//                    provider: YouTubePlayer.Provider,
//                    youTubeInitializationResult: YouTubeInitializationResult
//                ) {
////                    Toast.makeText(
////                        ApplicationProvider.getApplicationContext<Context>(),
////                        "Video player Failed",
////                        Toast.LENGTH_SHORT
////                    ).show()
//                }
//            })
//
//
//


    }

    private fun displayData(apodResponseDTO: APODResponseDTO) {
        tv_title_apod.text = apodResponseDTO.title
        tv_date_apod.text = apodResponseDTO.date
        tv_copyright_apod.text = "\u00A9 ${apodResponseDTO.copyright}"
        tv_explanation_apod_v.text = apodResponseDTO.explanation

        if (apodResponseDTO.media_type == "video") {
            wv_one_url_video_apod.visibility = View.VISIBLE
            iv_url_apod_v.visibility = View.GONE
            val mWebView = wv_one_url_video_apod
            mWebView.getSettings().setJavaScriptEnabled(true)
            mWebView.getSettings().setPluginState(WebSettings.PluginState.ON)
            mWebView.loadUrl(apodResponseDTO.url + "&autoplay=1&vq=small")
            mWebView.setWebChromeClient(WebChromeClient())
        } else {
            wv_one_url_video_apod.visibility = View.GONE
            iv_url_apod_v.visibility = View.VISIBLE
            val imageLoader = GladeImageLoader()
            apodResponseDTO.url?.let { imageLoader.loadInto(it, iv_url_apod_v) }
        }

//        iv_url_apod_v.setOnClickListener {
//            isExpanded = !isExpanded
//
//            // описание перехода
//            TransitionManager.beginDelayedTransition(
//                container_anim, TransitionSet()
//                    .addTransition(ChangeBounds())
//                    .addTransition(ChangeImageTransform())
//            )
//
//            // описание целевого состояния
//            val params: ViewGroup.LayoutParams = iv_url_apod_v.layoutParams
//
//            params.height = if (isExpanded) ViewGroup.LayoutParams.MATCH_PARENT
//            else ViewGroup.LayoutParams.WRAP_CONTENT
//
//            params.height = if (isExpanded) ViewGroup.VISIBLE
//            else ViewGroup.GONE
//
//            iv_url_apod_v.layoutParams = params  // на кой? работает и без
//
//            iv_url_apod_v.scaleType = if (isExpanded) ImageView.ScaleType.CENTER_CROP
//            else ImageView.ScaleType.FIT_CENTER
//
//
//            // описание целевого состояния
//            val params2: ViewGroup.LayoutParams = tv_explanation_apod_v.layoutParams
//
//            params2.height = if (isExpanded) ViewGroup.LayoutParams.MATCH_PARENT
//            else ViewGroup.LayoutParams.WRAP_CONTENT
//
//            params2.height = if (isExpanded) ViewGroup.VISIBLE
//            else ViewGroup.GONE
//
//            tv_explanation_apod_v.layoutParams = params  // на кой? работает и без


//            tv_explanation_apod_v.scaleType = if (isExpanded) ImageView.ScaleType.CENTER_CROP
//            else ImageView.ScaleType.FIT_CENTER

//        }


//            }
//            "video" -> {
//                wv_url_video_apod.getSettings().setJavaScriptEnabled(true)
//                wv_url_video_apod.getSettings().setPluginState(PluginState.ON)
//                wv_url_video_apod.loadUrl("https://www.youtube.com/embed/m8qvOpcDt1o?rel=0" + "?autoplay=1&vq=small")
//                wv_url_video_apod.setWebChromeClient(WebChromeClient())

//                val videoView = vv_true_video
//                val mediaController = MediaController(context)
//                mediaController.setAnchorView(videoView)
//                val uri = Uri.parse("https://www.youtube.com/embed/m8qvOpcDt1o?rel=0"+ "?autoplay=1&vq=small")
//                videoView.setMediaController(mediaController)
//                videoView.setVideoURI(uri)
//                videoView.requestFocus()
//
//                videoView.start()
//
//            }
//
//        }

    }


}



