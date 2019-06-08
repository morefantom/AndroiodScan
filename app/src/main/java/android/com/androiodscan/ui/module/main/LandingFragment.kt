package android.com.androiodscan.ui.module.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import retrofit2.Callback
import android.com.androiodscan.R
import android.com.androiodscan.data.ApiResponse
import android.com.androiodscan.repository.remote.RemoteController
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_landing.*
import retrofit2.Call
import retrofit2.Response

class LandingFragment : Fragment() {

    companion object {
        fun newInstance() = LandingFragment()
    }

    private var landingListAdapter: LandingListAdapter ?= null
    private var viewModel: LandingViewModel ?= null
    private var apiResponses = mutableListOf<ApiResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        landingListAdapter = LandingListAdapter(this.context, apiResponses)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_landing, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LandingViewModel::class.java)
        // TODO: Use the ViewModel
        RemoteController.apiResponseDao().getApiResponse().enqueue(object : Callback<List<ApiResponse>> {
            override fun onFailure(call: Call<List<ApiResponse>>, t: Throwable) {
                Log.i("Response Fail", t.message)
            }

            override fun onResponse(call: Call<List<ApiResponse>>, response: Response<List<ApiResponse>>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        apiResponses.addAll(it)
                        landingListAdapter?.swap(apiResponses)
                        landingListAdapter?.notifyDataSetChanged()
                    }
                }
                response.body()?.forEach { apiResponse ->
                    Log.i("Response Success", apiResponse.name)
                }
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycleView()
    }

    private fun setupRecycleView(){

        recyclerView_landing.apply {
            layoutManager = LinearLayoutManager(activity)
            landingListAdapter?.let {
                adapter = it
            }

        }
    }

}
