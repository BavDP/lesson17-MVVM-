package com.example.lesson17.mvvm.auth

import com.example.lesson17.models.UserAuth
import com.example.lesson17.storage.StorageService

class AuthRepository {
    fun userAuth(authInfo: UserAuth): Boolean {
        return if (authInfo.userName == "admin" && authInfo.password == "admin") {
            StorageService.getInstance()?.setAuthData(authInfo)
            true
        } else {
            false
        }
    }
}