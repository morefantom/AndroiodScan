package android.com.androiodscan.ui.module.detail

import android.com.androiodscan.data.Criteria
import androidx.recyclerview.widget.DiffUtil

class CardDiffCallback(
    private val oldList: List<Criteria>,
    private val newList: List<Criteria>
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
        return oldList[oldItemPosition].text == newList[newItemPosition].text
    }
}