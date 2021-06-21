package com.example.xumak.repository.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.xumak.repository.bd.dao.*
import com.example.xumak.repository.bd.entity.*
import com.example.xumak.repository.bd.typeConverter.ConvertersArrayInt
import com.example.xumak.repository.bd.typeConverter.ConvertersArrayString

@Database(
    entities = [CharacterItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ConvertersArrayInt::class, ConvertersArrayString::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun hitDao(): HitDAO
}


object DatabaseBuilder {
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "mindorks-example-coroutines"
        ).build()

}