package com.example.xumak.repository.bd.entity

import androidx.room.*

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity(tableName = "Character")
data class CharacterItem(
    @SerializedName("appearance") @ColumnInfo(name = "appearance") val appearance: ArrayList<Int>,
    @SerializedName("better_call_saul_appearance") @ColumnInfo(name = "better_call_saul_appearance") val betterCallSaulAppearance: ArrayList<Int>,
    @SerializedName("birthday") @ColumnInfo(name = "birthday") val birthday: String,
    @SerializedName("category") @ColumnInfo(name = "category") val category: String,
    @SerializedName("char_id") @PrimaryKey @ColumnInfo(name = "char_id") val charId: Int,
    @SerializedName("img") @ColumnInfo(name = "img") val img: String,
    @SerializedName("name") @ColumnInfo(name = "name") val name: String,
    @SerializedName("nickname") @ColumnInfo(name = "nickname") val nickname: String,
    @SerializedName("occupation") @ColumnInfo(name = "occupation") val occupation: ArrayList<String>,
    @SerializedName("portrayed") @ColumnInfo(name = "portrayed") val portrayed: String,
    @SerializedName("status") @ColumnInfo(name = "status") val status: String,
    @SerializedName("favourite") @ColumnInfo(name = "favourite") var favourite: Boolean
): Serializable {
    companion object {
        const val TABLE_NAME = "Character"
    }
}


