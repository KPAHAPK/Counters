package com.example.myapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_github_user")
data class RoomGitHubUser(
    @PrimaryKey
    @ColumnInfo(name = "u_id")
    val uId: String,

    @ColumnInfo(name = "login")
    val login: String?,

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String?
)



