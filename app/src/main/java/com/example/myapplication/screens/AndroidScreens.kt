package com.example.myapplication.screens

import com.example.myapplication.model.GitHubUser
import com.example.myapplication.model.UserRepository
import com.example.myapplication.view.UserDescriptionFragment
import com.example.myapplication.view.UserRepositoryInfoFragment
import com.example.myapplication.view.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users(): Screen {
        return FragmentScreen { UsersFragment.newInstance() }
    }

    override fun userDescription(userId: GitHubUser): Screen {
        return FragmentScreen { UserDescriptionFragment.newInstance(userId) }
    }

    override fun repositoryInfo(repo: UserRepository): Screen {
        return FragmentScreen { UserRepositoryInfoFragment.newInstance(repo) }
    }

}