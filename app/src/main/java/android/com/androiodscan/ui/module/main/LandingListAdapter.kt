package android.com.androiodscan.ui.module.main

import android.com.androiodscan.R
import android.com.androiodscan.data.ApiResponse
import android.com.androiodscan.data.MessageLanding
import android.com.androiodscan.databinding.ItemLandingBinding
import android.com.androiodscan.ui.interfaces.ClickingInterface
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.greenrobot.eventbus.EventBus

class LandingListAdapter(var context: Context?, private var apiResponses: List<ApiResponse>)
: RecyclerView.Adapter<LandingListAdapter.ViewHolder>(), ClickingInterface
{
    override fun onItemClick(apiResponse: ApiResponse) {
        EventBus.getDefault().post(MessageLanding(apiResponse))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemLandingBinding = DataBindingUtil.inflate(inflater, R.layout.item_landing,parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = apiResponses.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(apiResponses[position])
    }

    fun swap(apiResponses: MutableList<ApiResponse>){
        val diffCallback = CardDiffCallback(this.apiResponses, apiResponses)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.apiResponses = apiResponses
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(val binding: ItemLandingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(apiResponse: ApiResponse){
            binding.response = apiResponse
            binding.event = this@LandingListAdapter
            binding.executePendingBindings()
        }
    }

}