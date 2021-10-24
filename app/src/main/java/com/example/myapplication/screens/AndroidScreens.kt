package com.example.myapplication.screens

import com.example.myapplication.GitHubUser
import com.example.myapplication.view.UserDescriptionFragment
import com.example.myapplication.view.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users(): Screen {
        return FragmentScreen { UsersFragment.newInstance() }
    }

    override fun userDescription(user: GitHubUser): Screen {
        return FragmentScreen { UserDescriptionFragment.newInstance(user) }
    }
}