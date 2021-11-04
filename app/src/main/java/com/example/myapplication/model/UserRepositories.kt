package com.example.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepositories(
    @Expose val name: String?
) : Parcelable {
}