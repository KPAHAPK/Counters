package com.example.myapplication.model

import com.example.myapplication.INetworkStatus
import com.example.myapplication.api.IDataSource
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.database.RoomUserRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitUserRepositories(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val db: AppDatabase
) :
    IUserRepositories {
    override fun getUserRepos(user: GitHubUser): Single<List<UserRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUserRepositories(user.login).flatMap { userRepositories ->
                    Single.fromCallable {
                        val roomUserRepositories = userRepositories.map { userRepository ->
                            RoomUserRepository(
                                userRepository.id ?: "",
                                userRepository.name ?: "",
                                userRepository.forksCount ?: "",
                                user.id ?: ""
                            )
                        }
                        db.roomUserRepositoryDao().insertAll(roomUserRepositories)
                        userRepositories
                    }
                }
            } else {
                Single.fromCallable {
                    db.roomUserRepositoryDao().getAll().map { roomUserRepository ->
                        UserRepository(
                            roomUserRepository.id,
                            roomUserRepository.repoName,
                            roomUserRepository.forksCount
                        )
                    }
                }
            }
        }.subscribeOn(Schedulers.io())
}