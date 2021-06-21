package com.example.xumak.view.adapter

import android.content.Context
import android.content.Intent
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
import com.example.xumak.repository.models.CharacterItem
import com.example.xumak.view.ui.CharacterDetailActivity


class CharacterAdapter(private val dataSet: ArrayList<CharacterItem>, private val mContext: Context) :
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
        val item = dataSet[position]
        viewHolder.textViewTitle.text = item.name
        viewHolder.textViewDescription.text = item.nickname

        val scaleAnimation = ScaleAnimation(
            0.7f,
            1.0f,
            0.7f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.7f,
            Animation.RELATIVE_TO_SELF,
            0.7f
        )

        scaleAnimation.duration = 500;
        val bounceInterpolator = BounceInterpolator()
        scaleAnimation.interpolator = bounceInterpolator;

        viewHolder.buttonFavorite.setOnCheckedChangeListener { buttonView, isChecked ->
            buttonView.startAnimation(scaleAnimation)
            if (isChecked) {
                //Do Whatever you want in isChecked
            }
        }

        viewHolder.carHist.setOnClickListener {
            val submitIntent = Intent(mContext, CharacterDetailActivity::class.java)
            submitIntent.putExtra("item", item)
            mContext.startActivity(submitIntent)
        }
    }

    override fun getItemCount() = dataSet.size
}