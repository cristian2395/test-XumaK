package com.example.xumak.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.xumak.repository.api.Api
import com.example.xumak.repository.models.CharacterItem
import com.example.xumak.repository.models.ResponseRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val api = Api()
    val listCharacters: MutableLiveData<ArrayList<CharacterItem>> by lazy { MutableLiveData<ArrayList<CharacterItem>>() }
    val isRefreshing: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val msgSnack: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun peticionData(index: Int = 0) = CoroutineScope(Dispatchers.IO).launch {
        isRefreshing.postValue(true)
        withContext(Dispatchers.Main) {
            val result: ResponseRequest<ArrayList<CharacterItem>?> = api.GET("characters?limit=100&offset=$index", CharacterItem::class.java)
            if (result.status) {
                listCharacters.postValue(result.data)
            } else {
                msgSnack.postValue(result.message)
            }
            isRefreshing.postValue(false)
        }
    }
}