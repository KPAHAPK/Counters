package com.example.myapplication.database

import androidx.room.*

@Entity(tableName = "users")
data class User(
    @PrimaryKey val uId: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Insert
    fun insertAll(user: User)

    @Delete
    fun delete(user: User)
}

@Database(
    entities = [User::class], version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}