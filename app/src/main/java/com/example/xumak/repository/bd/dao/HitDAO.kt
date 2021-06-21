package com.example.xumak.repository.bd.dao

import androidx.room.*
import com.example.xumak.repository.bd.entity.CharacterItem

@Dao
interface HitDAO {

    @Query("SELECT * FROM " + CharacterItem.TABLE_NAME + " ORDER BY favourite DESC")
    suspend fun getAll(): List<CharacterItem>

    @Query("SELECT * FROM " + CharacterItem.TABLE_NAME + " WHERE favourite =:favourite")
    suspend fun getFavorite(favourite: Boolean): List<CharacterItem>

    @Insert
    suspend fun insertAll(character: List<CharacterItem>)

    @Update
    suspend fun update(character: CharacterItem)

    @Delete
    suspend fun delete(character: CharacterItem)

    @Query("DELETE FROM " + CharacterItem.TABLE_NAME)
    suspend fun deleteData()
}