package com.example.mysocialnetwork.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_info_table")
data class User(

    @PrimaryKey
    val userId: Int,

    @ColumnInfo(name = "users_full_name")
    var name: String,

    @ColumnInfo(name = "users_profile_photo")
    val profilePhoto: String,

    @ColumnInfo(name = "user_was_online_time")
    val wasOnline: String,

    @ColumnInfo(name = "users_hobby")
    var hobby: String,

    @ColumnInfo(name = "users_email")
    var email: String,

    @ColumnInfo(name = "users_status")
    var status: String
)