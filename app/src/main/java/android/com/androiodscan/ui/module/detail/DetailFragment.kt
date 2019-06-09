package android.com.androiodscan.ui.module.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.com.androiodscan.R
import android.com.androiodscan.data.ApiResponse
import android.com.androiodscan.databinding.DetailFragmentBinding
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {

    companion object {
        private var apiResponse: ApiResponse ?= null

        fun newInstance(apiResponse: ApiResponse): DetailFragment{
            DetailFragment.apiResponse = apiResponse
            return DetailFragment()
        }
    }

    private var binding: DetailFragmentBinding ?= null
    private var detailListAdapter: DetailListAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiResponse?.criteria?.let {
            detailListAdapter = DetailListAdapter(this.context, it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        binding?.response = apiResponse
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        recyclerView_detail.apply {
            layoutManager = LinearLayoutManager(activity)
            detailListAdapter?.let {
                adapter = it
            }
        }
    }
}
