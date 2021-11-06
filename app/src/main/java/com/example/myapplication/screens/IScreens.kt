package com.example.myapplication.screens

import com.example.myapplication.model.GitHubUser
import com.example.myapplication.model.UserRepository
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userDescription(user: GitHubUser): Screen
    fun repositoryInfo(repo: UserRepository): Screen
}

