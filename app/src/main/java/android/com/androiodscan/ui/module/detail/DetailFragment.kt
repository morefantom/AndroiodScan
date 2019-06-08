package android.com.androiodscan.ui.module.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.com.androiodscan.R
import android.com.androiodscan.data.ApiResponse
import android.com.androiodscan.databinding.DetailFragmentBinding
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {

    companion object {
        private var apiResponse: ApiResponse ?= null

        fun newInstance(apiResponse: ApiResponse): DetailFragment{
            DetailFragment.apiResponse = apiResponse
            return DetailFragment()
        }
    }

    private var viewModel: DetailViewModel ?= null
    private var binding: DetailFragmentBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        binding?.response = apiResponse
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
