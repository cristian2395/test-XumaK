package com.example.xumak.repository.bd

import com.example.xumak.repository.bd.entity.CharacterItem

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {
    override suspend fun getCharacter(): List<CharacterItem> = appDatabase.hitDao().getAll()
    override suspend fun getCharacterFavorite(favourite: Boolean): List<CharacterItem> = appDatabase.hitDao().getFavorite(favourite)
    override suspend fun insertAll(character: List<CharacterItem>) = appDatabase.hitDao().insertAll(character)
    override suspend fun updateCharacter(char: CharacterItem) = appDatabase.hitDao().update(char)
    override suspend fun deleteDataH() = appDatabase.hitDao().deleteData()
}