package com.example.mysocialnetwork.database

import androidx.room.*
import com.example.mysocialnetwork.user.User

@Dao
interface UserDatabaseDao {

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Query("SELECT * from users_info_table WHERE userId = :key")
    suspend fun get(key: Int): User?

    @Query("SELECT * from users_info_table")
    suspend fun getAll(): List<User>

    @Query("SELECT * from users_info_table LIMIT 1")
    suspend fun isEmpty(): User?

    @Delete
    suspend fun delete(user: User)
}