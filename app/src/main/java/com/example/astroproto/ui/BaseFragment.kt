package com.example.astroproto.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<V : ViewBinding>(
    private val inflaterBinding: (inflater: LayoutInflater, root: ViewGroup?, attachToRoot: Boolean) -> V
) : Fragment() {

    private var _binding: V? = null
    protected val binding: V get() = _binding!!
    protected var hasInitializedRootView: Boolean = false
    private var rootView: View? = null

    protected var isWritePermissionGranted = false
    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            isWritePermissionGranted = isGranted
        }

//    protected var hasInitializedRootView: Boolean = false
//    private var rootView: View? = null

    protected fun providePersistentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            _binding = inflaterBinding.invoke(inflater, container, false)
            rootView = binding.root
        } else {
            (rootView?.parent as? ViewGroup)?.removeView(rootView)
        }
        return rootView
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}