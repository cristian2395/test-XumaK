package com.example.xumak.repository.bd

import com.example.xumak.repository.bd.entity.*

interface DatabaseHelper {
    suspend fun getCharacter(): List<CharacterItem>
    suspend fun getCharacterFavorite(favourite: Boolean): List<CharacterItem>
    suspend fun insertAll(character: List<CharacterItem>)
    suspend fun updateCharacter(char: CharacterItem)
    suspend fun deleteDataH()
}