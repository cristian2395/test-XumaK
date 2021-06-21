package com.example.xumak.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xumak.repository.bd.DatabaseBuilder
import com.example.xumak.repository.bd.DatabaseHelperImpl
import com.example.xumak.repository.bd.entity.CharacterItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailViewModel(application: Application) : ViewModel() {

    private val dbHelper: DatabaseHelperImpl = DatabaseHelperImpl(DatabaseBuilder.getInstance(application))
    val msgSnack: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun updateCharter(character: CharacterItem) = CoroutineScope(Dispatchers.IO).launch {
        withContext(Dispatchers.Main) { dbHelper.updateCharacter(character) }
        msgSnack.postValue("Registro actualizado")
    }
}