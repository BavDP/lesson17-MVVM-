package com.example.lesson17.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.lesson17.models.Country
import com.example.lesson17.models.UserAuth
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URI
import java.net.URL

const val AUTH = "auth_data"
const val COUNTRIES = "countries_data"
class StorageService(private var context: Context) {
    private val pref: SharedPreferences by lazy {
        context.getSharedPreferences("StorageService", Context.MODE_PRIVATE)
    }
    companion object {
        private var instance: StorageService? = null

        fun getInstance(): StorageService? {
            return instance
        }

        fun init(context: Context) {
            if (instance == null) {
                instance = StorageService(context)
            } else {
                throw Exception("StorageService нельзя повторно инициализировать!")
            }
        }
    }

    init {
        pref.edit()
            .putString(
                COUNTRIES, Gson().toJson(listOf(
                Country("Ukraine", "https://cdn-icons-png.flaticon.com/512/197/197572.png"),
                Country("Germany", "https://cdn-icons-png.flaticon.com/512/197/197571.png"),
                Country("USA", "https://cdn-icons-png.flaticon.com/512/197/197484.png"),
                Country("Norway", "https://cdn-icons-png.flaticon.com/512/197/197579.png"),
                Country("Sweden", "https://cdn-icons-png.flaticon.com/512/197/197564.png"),
                Country("Canada", "https://cdn-icons-png.flaticon.com/512/197/197430.png"),
                Country("France", "https://cdn-icons-png.flaticon.com/512/197/197560.png"),
                Country("Italy", "https://cdn-icons-png.flaticon.com/512/323/323325.png"),
                Country("Finland", "https://cdn-icons-png.flaticon.com/512/197/197585.png"),
                Country("Great Britain", "https://cdn-icons-png.flaticon.com/512/197/197374.png"),
            )))
            .apply()
    }

    fun getAuthData(): UserAuth {
        return Gson().fromJson(pref.getString(AUTH, Gson().toJson(UserAuth())), UserAuth::class.java)
    }

    fun setAuthData(authData: UserAuth) {
        pref.edit()
            .putString(AUTH, Gson().toJson(authData).toString())
            .apply()
    }

    fun getCountries(): List<Country> {
        return Gson().fromJson(pref.getString(COUNTRIES, "[]"), object: TypeToken<List<Country>>(){}.type)
    }

}
