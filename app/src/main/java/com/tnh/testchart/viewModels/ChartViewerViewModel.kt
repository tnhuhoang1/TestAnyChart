package com.tnh.testchart.viewModels

import com.tnh.testchart.DataSource
import com.tnh.testchart.models.ChartData
import com.tnh.tnhlibrary.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChartViewerViewModel @Inject constructor(): BaseViewModel() {

    fun getChartByTitle(title: String): ChartData?{
        return DataSource.getData().find { it.title == title }
    }
}