package com.example.xumak.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xumak.repository.api.Api
import com.example.xumak.repository.bd.DatabaseBuilder
import com.example.xumak.repository.bd.DatabaseHelperImpl
import com.example.xumak.repository.bd.entity.CharacterItem
import com.example.xumak.repository.models.ResponseRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : ViewModel() {

    private val api = Api()
    private val dbHelper: DatabaseHelperImpl = DatabaseHelperImpl(DatabaseBuilder.getInstance(application))
    val listCharacters: MutableLiveData<ArrayList<CharacterItem>> by lazy { MutableLiveData<ArrayList<CharacterItem>>() }
    val isRefreshing: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val msgSnack: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun peticionData() = CoroutineScope(Dispatchers.IO).launch {
        isRefreshing.postValue(true)
        withContext(Dispatchers.Main) {
            val result: ResponseRequest<ArrayList<CharacterItem>?> = api.GET("characters?limit=150", CharacterItem::class.java)
            if (result.status) {
                result.data?.let {
                    val charFav = ArrayList(dbHelper.getCharacterFavorite(false))
                    val char = ArrayList(it)
                    dbHelper.deleteDataH()
                    dbHelper.insertAll(filterData(char, charFav) as List<CharacterItem>)
                    listCharacters.postValue(ArrayList(dbHelper.getCharacter()))
                }
            } else {
                msgSnack.postValue(result.message)
            }
            isRefreshing.postValue(false)
        }
    }

    fun updateCharter(character: CharacterItem) = CoroutineScope(Dispatchers.IO).launch {
        withContext(Dispatchers.Main) { dbHelper.updateCharacter(character) }
    }

    fun peticionDataBD() = CoroutineScope(Dispatchers.IO).launch {
        withContext(Dispatchers.Main) {
            listCharacters.postValue(ArrayList(dbHelper.getCharacter()))
            peticionData()
        }
    }

    private fun filterData(item: ArrayList<CharacterItem>, delete: ArrayList<CharacterItem>): ArrayList<CharacterItem> {
        return if (delete.size > 0) {
            item.map { apiItem->
                apiItem.favourite = delete.find{ apiItem.charId == it.charId }?.let { false } ?:run { true }
            }
            return item

        } else {
            item
        }
    }
}