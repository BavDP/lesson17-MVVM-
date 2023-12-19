package com.example.lesson17.mvvm.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson17.models.Country

class CountryViewModel(private val repository: CountryRepository): ViewModel() {
    private var _countriesLiveData = MutableLiveData<List<Country>>()
    val countriesLiveData: LiveData<List<Country>> = _countriesLiveData
    fun requestCountriesList() {
       _countriesLiveData.value = repository.loadCountryList()
    }
}