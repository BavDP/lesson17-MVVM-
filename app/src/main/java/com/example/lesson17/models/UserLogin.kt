package com.example.lesson17.models

data class UserAuth(var userName: String = "", var password: String = "", var authSuccess: Boolean = false) {
    fun isEmpty(): Boolean {
        return userName == "" || password == ""
    }
}