package com.example.xumak.view.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xumak.databinding.ActivityMainBinding
import com.example.xumak.R
import com.example.xumak.view.adapter.CharacterAdapter
import com.example.xumak.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerViewAdapter: CharacterAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = MainViewModel(this.application)

        // Inicialización Toolbar
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        toolbar.title = "Breaking Bad Characters"

        // Inicialización widget
        binding.swipeRefresh.isRefreshing = true
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        binding.swipeRefresh.setOnRefreshListener {
            viewModel.peticionData()
        }

        //Live Data
        viewModel.listCharacters.observeForever {
            recyclerViewAdapter = CharacterAdapter(it, this)
            binding.recyclerView.adapter = recyclerViewAdapter
        }
        viewModel.isRefreshing.observeForever {
            binding.swipeRefresh.isRefreshing = it
        }
        viewModel.msgSnack.observeForever {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show();
        }

        viewModel.peticionData()
    }
}