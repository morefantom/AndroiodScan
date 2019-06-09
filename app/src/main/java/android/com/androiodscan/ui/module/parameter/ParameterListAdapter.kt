package android.com.androiodscan.ui.module.parameter

import android.com.androiodscan.R
import android.com.androiodscan.databinding.ItemParameterBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class ParameterListAdapter(private var values: List<Float>): RecyclerView.Adapter<ParameterListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemParameterBinding = DataBindingUtil.inflate(inflater, R.layout.item_parameter, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values[position])
    }


    inner class ViewHolder(val binding: ItemParameterBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(value: Float){

            binding.value = if (value%1 == 0f){
                value.toInt().toString()
            }else{
                value.toString()
            }
        }
    }
}