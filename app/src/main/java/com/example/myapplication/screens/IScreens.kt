package com.example.myapplication.screens

import com.example.myapplication.model.GitHubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userDescription(user: GitHubUser): Screen
}

