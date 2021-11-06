package com.example.myapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "room_user_repository", foreignKeys = [ForeignKey(
        entity = RoomGitHubUser::class,
        parentColumns = ["uId"],
        childColumns = ["ownerId"],
        onDelete = ForeignKey.CASCADE

    )]
)
class RoomUserRepository(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "repo_name") val repoName: String,
    @ColumnInfo(name = "forks_count") val forksCount: String,
    @ColumnInfo(name = "owner_id") val ownerId: String
) {
}