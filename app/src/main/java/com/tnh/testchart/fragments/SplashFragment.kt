package com.tnh.testchart.fragments

import androidx.navigation.fragment.findNavController
import com.tnh.testchart.R
import com.tnh.testchart.databinding.SplashFragmentBinding
import com.tnh.tnhlibrary.dataBinding.RetainedDataBindingFragment

class SplashFragment: RetainedDataBindingFragment<SplashFragmentBinding>(R.layout.splash_fragment) {

    override fun doOnCreateView() {
        binding.splashFragmentStart.setOnClickListener{
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToChartGraph())
        }
    }
}