package com.example.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepository(
    @Expose val name: String?,
    @Expose val forks: Int?
) : Parcelable {
}
