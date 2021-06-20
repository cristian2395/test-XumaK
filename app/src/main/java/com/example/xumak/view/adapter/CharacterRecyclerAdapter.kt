package com.example.xumak.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xumak.R
import com.example.xumak.databinding.ItemCharacterBinding
import com.example.xumak.databinding.ItemLoadingBinding
import com.example.xumak.repository.models.CharacterItem
import java.util.*

class CharacterRecyclerAdapter(private val mCharacterItems: MutableList<CharacterItem>) : RecyclerView.Adapter<BaseViewHolder>() {
    private var isLoaderVisible = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
            )
            VIEW_TYPE_LOADING -> ProgressHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            )
            else -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoaderVisible) {
            if (position == mCharacterItems.size - 1) VIEW_TYPE_LOADING else VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    override fun getItemCount(): Int {
        return mCharacterItems?.size ?: 0
    }

    fun addItems(postItems: List<CharacterItem>?) {
        mCharacterItems!!.addAll(postItems!!)
        notifyDataSetChanged()
    }

    fun addLoading() {
        isLoaderVisible = true
        mCharacterItems!!.add(
            CharacterItem(
                ArrayList<Int>(),
                ArrayList<Int>(),
                "A",
                "A",
                0,
                "A",
                "A",
                "A",
                ArrayList<String>(),
                "A",
                "A"
            )
        )
        notifyItemInserted(mCharacterItems.size - 1)
    }

    fun removeLoading() {
        isLoaderVisible = false
        val position = mCharacterItems!!.size - 1
        val item = getItem(position)
        if (item != null) {
            mCharacterItems.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        mCharacterItems!!.clear()
        notifyDataSetChanged()
    }

    fun getItem(position: Int): CharacterItem {
        return mCharacterItems!![position]
    }

    inner class ViewHolder internal constructor(itemView: View?) : BaseViewHolder(itemView) {
        var binding: ItemCharacterBinding
        var textViewTitle: TextView
        var textViewDescription: TextView
        override fun clear() {}
        override fun onBind(position: Int) {
            super.onBind(position)
            val (_, _, _, _, _, _, name, nickname) = mCharacterItems!![position]
            textViewTitle.text = name
            textViewDescription.text = nickname
        }

        init {
            binding = ItemCharacterBinding.bind(itemView!!)
            textViewTitle = binding.textName
            textViewDescription = binding.textNickname
        }
    }

    inner class ProgressHolder internal constructor(itemView: View?) : BaseViewHolder(itemView) {
        var binding: ItemLoadingBinding
        override fun clear() {}

        init {
            binding = ItemLoadingBinding.bind(itemView!!)
        }
    }

    companion object {
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_NORMAL = 1
    }
}