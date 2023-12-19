package com.example.lesson17.mvvm.countries

import com.example.lesson17.models.Country
import com.example.lesson17.storage.StorageService

class CountryRepository {
    fun loadCountryList(): List<Country> {
        return StorageService.getInstance()?.getCountries()?: listOf()
    }
}