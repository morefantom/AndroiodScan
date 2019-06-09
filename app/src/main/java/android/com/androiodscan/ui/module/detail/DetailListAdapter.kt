package android.com.androiodscan.ui.module.detail

import android.com.androiodscan.R
import android.com.androiodscan.data.Criteria
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.com.androiodscan.databinding.ItemDetailBinding

class DetailListAdapter(var context: Context?, private var criterias: List<Criteria>)
    : RecyclerView.Adapter<DetailListAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemDetailBinding = DataBindingUtil.inflate(inflater, R.layout.item_detail,parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = criterias.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(criterias[position], criterias.size-1 > position)
    }

    inner class ViewHolder(val binding: ItemDetailBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(criteria: Criteria, isLast: Boolean){
            binding.criteria = criteria
            binding.isLast = isLast
            binding.executePendingBindings()
        }
    }

}