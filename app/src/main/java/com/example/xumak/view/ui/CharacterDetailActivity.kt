package com.example.xumak.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.xumak.R
import com.example.xumak.databinding.ActivityCharacterDetailBinding
import com.example.xumak.repository.models.CharacterItem
import com.squareup.picasso.Picasso

class CharacterDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityCharacterDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Toolbar
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        // Get putExtra
        val item: CharacterItem? = intent?.getSerializableExtra("item") as CharacterItem

        toolbar.title = item?.name
        binding.textNickname.text = item?.nickname
        binding.textOccupation.text = item?.occupation?.joinToString(",")
        binding.textStatus.text = item?.status
        binding.textPortrayed .text= item?.portrayed
        Picasso.get().load(item?.img).fit()
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.imageView)

    }
}