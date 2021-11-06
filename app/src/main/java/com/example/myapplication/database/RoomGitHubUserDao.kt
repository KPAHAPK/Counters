package com.example.myapplication.database

import androidx.room.*
import com.example.myapplication.model.UserRepository

@Dao
interface RoomGitHubUserDao {

    @Query("SELECT * FROM room_github_user")
    fun getAll(): List<RoomGitHubUser>

    @Query("SELECT * FROM room_github_user WHERE uId IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<RoomGitHubUser>

    @Query("SELECT * FROM room_github_user WHERE login = :login LIMIT 1")
    fun findByLogin(login: String): RoomGitHubUser

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomGitHubUser: RoomGitHubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg roomGitHubUser: RoomGitHubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(roomGitHubUsers: List<RoomGitHubUser>)

    @Update
    fun update(roomGitHubUser: RoomGitHubUser)

    @Update
    fun update(vararg roomGitHubUser: RoomGitHubUser)

    @Update
    fun updateAll(roomGitHubUsers: List<RoomGitHubUser>)

    @Delete
    fun delete(roomGitHubUser: RoomGitHubUser)

    @Delete
    fun delete(vararg roomGitHubUser: RoomGitHubUser)

    @Delete
    fun deleteAll(roomGitHubUsers: List<RoomGitHubUser>)
}