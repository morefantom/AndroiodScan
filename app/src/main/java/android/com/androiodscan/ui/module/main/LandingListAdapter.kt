package android.com.androiodscan.ui.module.main

import android.com.androiodscan.R
import android.com.androiodscan.data.ApiResponse
import android.com.androiodscan.databinding.ItemLandingBinding
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class LandingListAdapter(var context: Context?, private var apiResponses: List<ApiResponse>)
: RecyclerView.Adapter<LandingListAdapter.ViewHolder>()
{

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
//            binding.event = this@LandingListAdapter
            binding.executePendingBindings()
        }
    }
//
//    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
//        EventBus.getDefault().register(this)
//        super.onAttachedToRecyclerView(recyclerView)
//    }
//
//    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
//        EventBus.getDefault().unregister(this)
//        super.onDetachedFromRecyclerView(recyclerView)
//    }
}