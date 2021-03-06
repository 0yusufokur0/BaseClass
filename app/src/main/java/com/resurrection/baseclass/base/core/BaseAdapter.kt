package com.resurrection.imkb.ui.base.core

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.resurrection.baseclass.base.AppSession
import javax.inject.Inject

open class BaseAdapter<T, viewDataBinding : ViewDataBinding>(
    var layoutResource: Int,
    var currentList: ArrayList<T>,
    var itemId: Int,
    var onItemClick: (T) -> Unit
) : RecyclerView.Adapter<BaseAdapter.BaseHolder<T>>(),Filterable {

    @Inject
    lateinit var appSession: AppSession

    lateinit var binding: viewDataBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<T> {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutResource,
            parent,
            false
        )
        return BaseHolder(binding, itemId, onItemClick)
    }

    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) { holder.bind(currentList[position]) }

    override fun getItemCount() = currentList.size

    fun updateList(newList: ArrayList<T>) {
        val diffCallBack = BaseDiffUtil<T>(currentList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        diffResult.dispatchUpdatesTo(this)
    }

/*    fun removeItem(item: T) {
        val pos = getItemPosition(item)
        if (pos > -1) {
            currentList.remove(item)
            notifyItemRemoved(pos)
            notifyItemRangeChanged(pos, itemCount)
        }
    }

    fun addItem(item: T) {
        val pos = getItemPosition(item)
        if (pos > -1) {
            currentList.add(item)
            notifyItemInserted(pos)
            notifyItemRangeChanged(pos, itemCount)
        }
    }*/

    class BaseHolder<T>(
        private var binding: ViewDataBinding,
        private var itemId: Int,
        var onItemClick: (T) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            binding.setVariable(itemId, item)
            itemView.setOnClickListener { onItemClick((item)) }
        }
    }

    class BaseDiffUtil<T>(
        private val oldList: List<T>,
        private val newList: List<T>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldPosition: Int, newPosition: Int) = oldList[oldPosition] == newList[newPosition]
        override fun areContentsTheSame(oldPosition: Int, newPosition: Int) = oldList[oldPosition] == newList[newPosition]
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = ArrayList<T>()
                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(currentList)
                } else {
                    val filterPattern = constraint.toString().toLowerCase().trim()
                    for (item in currentList) {
                        if (item.toString().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                currentList.clear()
                currentList.addAll(results?.values as ArrayList<T>)
                notifyDataSetChanged()
            }
        }
    }
}

