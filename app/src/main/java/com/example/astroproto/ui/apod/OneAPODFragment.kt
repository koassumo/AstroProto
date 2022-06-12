package com.example.astroproto.ui.apod

import android.graphics.PointF
import android.graphics.Rect
import android.os.Bundle
import android.view.*
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import coil.load
import com.example.astroproto.R
import com.example.astroproto.databinding.OneApodFragmentBinding
import com.example.astroproto.entity.APODResponseDTO

class OneAPODFragment : Fragment (){

    companion object {

        //константа для передачи данных из фрагмента во фрагмент
        const val APOD_RESPONSE_DTO_EXTRA = "APOD_RESPONSE_DTO_EXTRA"

        //константы для жестов (ч. 1 из 3)
        private const val MAX_SCALE_FACTOR = 5f
        private const val MIN_SCALE_FACTOR = 1f
        private const val CORRECT_LOCATION_ANIMATION_DURATION = 300L

        // фабричный метод
        fun newInstance(bundle: Bundle): OneAPODFragment =
            OneAPODFragment().apply { arguments = bundle }
    }


    //private lateinit var viewModel: OneAPODViewModel
    //private lateinit var apodResponseBundle: APODResponseTEMP
    private var isExpanded = false


    private var _binding: OneApodFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    // 5. см. ниже добавить строки в onCreateView




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.one_apod_fragment, container, false)
        _binding = OneApodFragmentBinding.bind(view)
        return binding.root
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


        // Методы, относящиеся к жестам (ч. 2 из 3)
        binding.ivUrlApodV.doOnLayout { originContentRect }
        binding.viewTouchHandler.setOnTouchListener { view, event ->
            scaleGestureDetector.onTouchEvent(event)
            translationHandler.onTouch(view, event)
            true
        }
    }


    ///sasafdsadfsdafdasfda
    private fun displayData(apodResponseDTO: APODResponseDTO) {
        binding.tvTitleApod.text = apodResponseDTO.title
        binding.tvDateApod.text = apodResponseDTO.date
        binding.tvCopyrightApod.text = "\u00A9 ${apodResponseDTO.copyright}"
        binding.tvExplanationApodV.text = apodResponseDTO.explanation

        val unit = if (apodResponseDTO.media_type == "video") {
            binding.wvOneUrlVideoApod.visibility = View.VISIBLE
            binding.ivUrlApodV.visibility = View.GONE
            val mWebView = binding.wvOneUrlVideoApod
            mWebView.getSettings().setJavaScriptEnabled(true)
            mWebView.getSettings().setPluginState(WebSettings.PluginState.ON)
            mWebView.loadUrl(apodResponseDTO.url + "&autoplay=1&vq=small")
            mWebView.setWebChromeClient(WebChromeClient())
        } else {
//            wv_one_url_video_apod.visibility = View.GONE
//            iv_url_apod_v.visibility = View.VISIBLE
            binding.ivUrlApodV.load(apodResponseDTO.url)
        }
    }


    // Ниже методы, относящиеся к жестам (ч. 3 из 3)
    private val originContentRect by lazy {
        binding.ivUrlApodV.run {
            val array = IntArray(2)
            getLocationOnScreen(array)
            Rect(array[0], array[1], array[0] + width, array[1] + height)
        }
    }

    private val translationHandler by lazy {
        object : View.OnTouchListener {
            private var prevX = 0f
            private var prevY = 0f
            private var moveStarted = false
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event == null || (binding.ivUrlApodV?.scaleX ?: 1f) == 1f) return false

                when (event.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        prevX = event.x
                        prevY = event.y
                    }

                    MotionEvent.ACTION_POINTER_UP -> {
                        if (event.actionIndex == 0) {
                            try {
                                prevX = event.getX(1)
                                prevY = event.getY(1)
                            } catch (e: Exception) {
                            }
                        }
                    }

                    MotionEvent.ACTION_MOVE -> {
                        if (event.pointerCount > 1) {
                            prevX = event.x
                            prevY = event.y
                            return false
                        }
                        moveStarted = true
                        binding.ivUrlApodV?.run {
                            translationX += (event.x - prevX)
                            translationY += (event.y - prevY)
                        }
                        prevX = event.x
                        prevY = event.y
                    }

                    MotionEvent.ACTION_UP -> {
                        if (!moveStarted) return false
                        reset()
                        translateToOriginalRect()
                    }
                }
                return true
            }

            private fun reset() {
                prevX = 0f
                prevY = 0f
                moveStarted = false
            }
        }
    }

    private val scaleGestureDetector by lazy {
        ScaleGestureDetector(context, object : ScaleGestureDetector.OnScaleGestureListener {
            var totalScale = 1f

            override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
                binding.ivUrlApodV.run {
                    val actualPivot = PointF(
                        (detector.focusX - translationX + pivotX * (totalScale - 1)) / totalScale,
                        (detector.focusY - translationY + pivotY * (totalScale - 1)) / totalScale,
                    )

                    translationX -= (pivotX - actualPivot.x) * (totalScale - 1)
                    translationY -= (pivotY - actualPivot.y) * (totalScale - 1)
                    setPivot(actualPivot)
                }
                return true
            }

            override fun onScale(detector: ScaleGestureDetector): Boolean {
                totalScale *= detector.scaleFactor
                totalScale = totalScale.coerceIn(MIN_SCALE_FACTOR, MAX_SCALE_FACTOR)
                binding.ivUrlApodV.run {
                    scale(totalScale)
                    getContentViewTranslation().run {
                        translationX += x
                        translationY += y
                    }
                }
                return true
            }

            override fun onScaleEnd(detector: ScaleGestureDetector) = Unit
        })
    }

    private fun translateToOriginalRect() {
        getContentViewTranslation().takeIf { it != PointF(0f, 0f) }?.let { translation ->
            binding.ivUrlApodV?.let { view ->
                view.animateWithDetach()
                    .translationXBy(translation.x)
                    .translationYBy(translation.y)
                    .apply { duration = CORRECT_LOCATION_ANIMATION_DURATION }
                    .start()
            }
        }
    }

    private fun getContentViewTranslation(): PointF {
        return binding.ivUrlApodV.run {
            originContentRect.let { rect ->
                val array = IntArray(2)
                getLocationOnScreen(array)
                PointF(
                    when {
                        array[0] > rect.left -> rect.left - array[0].toFloat()
                        array[0] + width * scaleX < rect.right -> rect.right - (array[0] + width * scaleX)
                        else -> 0f
                    },
                    when {
                        array[1] > rect.top -> rect.top - array[1].toFloat()
                        array[1] + height * scaleY < rect.bottom -> rect.bottom - (array[1] + height * scaleY)
                        else -> 0f
                    }
                )
            }
        }
    }



}



