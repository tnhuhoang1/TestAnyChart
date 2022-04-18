package com.tnh.testchart.models

import com.anychart.core.Chart
import com.tnh.tnhlibrary.dataBinding.recycler.IdModel

data class ChartData(
    val title: String,
    val thumbnail: String,
    val chart: Chart
): IdModel{
    override val identification: String
        get() = title
}