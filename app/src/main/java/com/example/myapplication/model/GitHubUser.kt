package com.example.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitHubUser(
    @Expose val id: Int,
    @Expose val login: String,
    @Expose val avatarUrl: String
) : Parcelable

