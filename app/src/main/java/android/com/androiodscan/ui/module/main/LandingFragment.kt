package android.com.androiodscan.ui.module.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import retrofit2.Callback
import android.com.androiodscan.R
import android.com.androiodscan.data.ApiResponse
import android.com.androiodscan.repository.remote.RemoteController
import android.com.androiodscan.util.DividerItemDecoration
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_landing.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class LandingFragment : Fragment(), CoroutineScope {

    private var landingListAdapter: LandingListAdapter? = null
    private var apiResponses = listOf<ApiResponse>()

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
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
        launch {
            fetchAndPopulate()
        }
    }

    private suspend fun fetchAndPopulate() {
        apiResponses = withContext(Dispatchers.IO) { RemoteController.apiResponseDao().getApiResponse() }
        landingListAdapter?.swap(apiResponses)
        landingListAdapter?.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycleView()
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    private fun setupRecycleView() {

        recyclerView_landing.apply {
            layoutManager = LinearLayoutManager(activity)
            landingListAdapter?.let {
                adapter = it
            }
            val dividerDecoration = DividerItemDecoration(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.horizontal_divider
                )
            )
            addItemDecoration(dividerDecoration)
        }
    }

}
