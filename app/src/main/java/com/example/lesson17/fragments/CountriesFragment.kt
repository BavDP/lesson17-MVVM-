package com.example.lesson17.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson17.R
import com.example.lesson17.adapters.CountriesAdapter
import com.example.lesson17.mvvm.countries.CountryModelFactory
import com.example.lesson17.mvvm.countries.CountryViewModel

class CountriesFragment : Fragment() {
    private lateinit var rv: RecyclerView
    private lateinit var viewModel: CountryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv = view.findViewById(R.id.countriesList)
        rv.adapter = CountriesAdapter()
        rv.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        val provider = ViewModelProvider(this, CountryModelFactory())
        viewModel = provider.get(CountryViewModel::class.java)
        viewModel.requestCountriesList()
        initObserves()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    private fun initObserves() {
        viewModel.countriesLiveData.observe(viewLifecycleOwner) {
            (rv.adapter as CountriesAdapter).setCountries(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CountriesFragment()
    }
}