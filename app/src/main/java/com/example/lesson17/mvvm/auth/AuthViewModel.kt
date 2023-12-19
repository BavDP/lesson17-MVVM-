package com.example.lesson17.mvvm.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson17.models.UserAuth

class AuthViewModel(private val repository: AuthRepository): ViewModel() {
    val authLiveData = MutableLiveData<UserAuth>()
    fun submitAuthInfo(authInfo: UserAuth) {
        if (!authInfo.isEmpty()) {
            if (repository.userAuth(authInfo)) {
                authInfo.authSuccess = true
                authInfo.password = ""
                authLiveData.value = authInfo
            }
        }
    }

    fun userAuthDone() {
        authLiveData.value = UserAuth(authLiveData.value?.userName?:"", "", false)
    }
}