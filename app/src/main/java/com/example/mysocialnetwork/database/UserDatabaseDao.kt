package com.example.mysocialnetwork.database

import androidx.room.*
import com.example.mysocialnetwork.user.User

@Dao
interface UserDatabaseDao {

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Query("SELECT * from users_info_table_2 WHERE userId = :key")
    suspend fun get(key: Int): User?

    @Query("SELECT * from users_info_table_2")
    suspend fun getAll(): List<User>

    @Query("SELECT * from users_info_table_2 LIMIT 1")
    suspend fun isEmpty(): User?

    @Delete
    suspend fun delete(user: User)
}