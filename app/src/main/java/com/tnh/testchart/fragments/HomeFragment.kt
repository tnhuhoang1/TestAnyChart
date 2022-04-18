package com.tnh.testchart.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.anychart.AnyChart
import com.tnh.testchart.DataSource
import com.tnh.testchart.R
import com.tnh.testchart.adapters.ChartAdapter
import com.tnh.testchart.databinding.HomeFragmentBinding
import com.tnh.tnhlibrary.dataBinding.RetainedDataBindingFragment
import com.tnh.tnhlibrary.liveData.utils.safeObserve
import com.tnh.tnhlibrary.utils.logAny
import com.tnh.tnhlibrary.view.show
import com.tnh.tnhlibrary.view.snackbar.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragment: RetainedDataBindingFragment<HomeFragmentBinding>(R.layout.home_fragment) {
    private val viewModel by navGraphViewModels<HomeViewModel>(R.id.chart_graph)

    val chartAdapter by lazy {
        ChartAdapter().apply {
            onItemClicked = {chartItem ->
                navigateToChartViewer(chartItem.title)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.homeFragmentRecycler.adapter = chartAdapter
        getData()
        testLiveDataAndFlow()
    }

    private fun getData() {
        lifecycleScope.launchWhenCreated {
            withContext(Dispatchers.Default) {

                DataSource.getData()
            }.let {
                chartAdapter.submitList(it)
            }
        }
    }

    private fun navigateToChartViewer(title: String){
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToChartViewerFragment(title))
    }

    private fun testLiveDataAndFlow(){
        safeObserve(viewModel.testLiveData){
            it.logAny()
        }
        lifecycleScope.launchWhenCreated {
            viewModel.intervalFlow.collectLatest {
                "Collect interval on created $it".logAny()
            }
        }

        lifecycleScope.launch {
            viewModel.intervalFlow.collectLatest {
                "Collect interval $it".logAny()
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.intervalFlow.collectLatest {
                "Collect interval on resumed $it".logAny()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collectLatest {
                it.logAny()
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.sharedFlow.collectLatest {
                binding.root.showSnackBar(it)
            }
        }
    }
}