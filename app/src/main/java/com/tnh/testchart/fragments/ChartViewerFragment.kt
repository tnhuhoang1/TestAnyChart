package com.tnh.testchart.fragments

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.tnh.testchart.R
import com.tnh.testchart.databinding.ChartViewerFragmentBinding
import com.tnh.testchart.viewModels.ChartViewerViewModel
import com.tnh.tnhlibrary.dataBinding.RetainedDataBindingFragment
import com.tnh.tnhlibrary.utils.logAny
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChartViewerFragment: RetainedDataBindingFragment<ChartViewerFragmentBinding>(R.layout.chart_viewer_fragment) {
    private val viewModel by viewModels<ChartViewerViewModel>()
    private val homeViewModel by navGraphViewModels<HomeViewModel>(R.id.chart_graph)
    private val args by navArgs<ChartViewerFragmentArgs>()

    override fun doOnCreateView() {
        val anyChart = binding.chartViewerFragmentChart
        viewModel.getChartByTitle(args.chartTitle)?.let {
            anyChart.setChart(it.chart)
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.test.logAny()
    }
}