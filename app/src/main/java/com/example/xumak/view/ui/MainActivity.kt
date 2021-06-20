package com.example.xumak.view.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.xumak.databinding.ActivityMainBinding
import com.example.xumak.R
import com.example.xumak.view.adapter.CharacterRecyclerAdapter


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mRecyclerView: RecyclerView
    lateinit var adapter: CharacterRecyclerAdapter
    lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Inicialización Toolbar
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        toolbar.title = "Breaking Bad Characters"

        // Inicialización widget
        mRecyclerView = binding.recyclerView
        swipeRefresh = binding.swipeRefresh
    }
}