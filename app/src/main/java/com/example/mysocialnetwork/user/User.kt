package com.example.mysocialnetwork.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_info_table")
data class User(

    @PrimaryKey
    val userId: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "profile_photo")
    val profilePhoto: String,

    @ColumnInfo(name = "was_online")
    val wasOnline: String,

    @ColumnInfo(name = "hobby")
    var hobby: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "status")
    var status: String
)