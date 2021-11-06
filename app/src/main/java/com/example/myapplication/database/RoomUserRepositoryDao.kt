package com.example.myapplication.database

import androidx.room.*
import com.example.myapplication.model.UserRepository

@Dao
interface RoomUserRepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomUserRepository: RoomUserRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg roomUserRepository: RoomUserRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(roomUserRepository: List<RoomUserRepository>)

    @Update
    fun update(roomUserRepository: RoomUserRepository)

    @Update
    fun update(vararg roomUserRepository: RoomUserRepository)

    @Update
    fun updateAll(roomUserRepository: List<RoomUserRepository>)

    @Delete
    fun delete(roomUserRepository: RoomUserRepository)

    @Delete
    fun delete(vararg roomUserRepository: RoomUserRepository)

    @Delete
    fun deleteAll(roomUserRepository: List<RoomUserRepository>)

    @Query("SELECT * FROM room_user_repository")
    fun getAll(): List<RoomUserRepository>

    @Query("SELECT * FROM room_user_repository WHERE owner_id = :ownerId")
    fun getAllUserRepositories(ownerId: String): List<UserRepository>

}