package com.example.lesson17.mvvm.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson17.models.UserAuth

class AuthViewModel(private val repository: AuthRepository): ViewModel() {
    private var _authLiveData = MutableLiveData<UserAuth>()
    val authLiveData: LiveData<UserAuth> = _authLiveData
    fun submitAuthInfo(authInfo: UserAuth) {
        if (!authInfo.isEmpty()) {
            if (repository.userAuth(authInfo)) {
                authInfo.authSuccess = true
                authInfo.password = ""
                _authLiveData.value = authInfo
            }
        }
    }

    fun userAuthDone() {
        _authLiveData.value = UserAuth(authLiveData.value?.userName?:"", "", false)
    }
}