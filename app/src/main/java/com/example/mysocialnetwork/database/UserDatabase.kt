package com.example.mysocialnetwork.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mysocialnetwork.user.User

@Database(entities = [User::class], version = 3)
abstract class UserDatabase : RoomDatabase() {

    abstract val userDatabaseDao: UserDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "users_info_table"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
