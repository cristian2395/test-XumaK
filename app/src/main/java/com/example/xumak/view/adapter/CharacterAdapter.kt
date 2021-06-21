package com.example.xumak.view.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.example.xumak.R
import com.example.xumak.databinding.ItemCharacterBinding
import com.example.xumak.repository.bd.entity.CharacterItem
import com.example.xumak.repository.bd.extension.heartEffect
import com.example.xumak.view.ui.CharacterDetailActivity
import com.example.xumak.viewModel.MainViewModel


class CharacterAdapter(private val dataSet: ArrayList<CharacterItem>, private val mContext: Context, private val viewModel: MainViewModel) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ItemCharacterBinding = ItemCharacterBinding.bind(itemView)
        var textViewTitle: TextView = binding.textName
        var textViewDescription: TextView = binding.textNickname
        var buttonFavorite: ToggleButton = binding.buttonFavorite
        var carHist: LinearLayout = binding.carHist
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_character, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        //viewHolder.buttonFavorite.isChecked = false
        viewHolder.textViewTitle.text = dataSet[position].name
        viewHolder.textViewDescription.text = dataSet[position].nickname
        viewHolder.buttonFavorite.isChecked = dataSet[position].favourite

        viewHolder.buttonFavorite.heartEffect()

        viewHolder.buttonFavorite.setOnClickListener {
            dataSet[position].favourite = viewHolder.buttonFavorite.isChecked
            viewModel.updateCharter(dataSet[position])
        }

        viewHolder.carHist.setOnClickListener {
            val submitIntent = Intent(mContext, CharacterDetailActivity::class.java)
            submitIntent.putExtra("item", dataSet[position])
            mContext.startActivity(submitIntent)
        }
    }

    override fun getItemCount() = dataSet.size
}