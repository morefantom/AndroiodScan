package android.com.androiodscan.ui.module.main

import android.com.androiodscan.data.ApiResponse
import androidx.recyclerview.widget.DiffUtil

class CardDiffCallback(
    private val oldList: List<ApiResponse>,
    private val newList: List<ApiResponse>
): DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }
}