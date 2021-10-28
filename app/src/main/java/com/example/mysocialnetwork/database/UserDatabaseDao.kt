package com.example.mysocialnetwork.database

import androidx.room.*
import com.example.mysocialnetwork.user.User

@Dao
interface UserDatabaseDao {

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("SELECT * from users_info_table WHERE userId = :key")
    fun get(key: Int): User?

    @Query("SELECT * from users_info_table")
    fun getAll(): List<User>

    @Query("SELECT * from users_info_table LIMIT 1")
    fun isEmpty(): User?

    @Delete
    fun delete(user: User)
}