package com.waterbase.from.ulkeleruygulamasi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.waterbase.from.ulkeleruygulamasi.databinding.FragmentCountryBinding
import com.waterbase.from.ulkeleruygulamasi.viewmodel.CountryViewModel
import timber.log.Timber

class CountryFragment : Fragment() {

    private lateinit var viewModel: CountryViewModel

    private var _binding: FragmentCountryBinding? = null
    private val binding get() = _binding!!

    private var countryUuid = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom()

        observeLiveData()

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
            Timber.e("gelen id : $countryUuid")
        }
        binding.apply {

        }
    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                binding.apply {
                    tvCountryName.text = country.name
                    tvCapital.text = country.capital
                    tvRegion.text = country.region
                    tvCurrency.text = country.currency
                    tvLanguage.text = country.language
                    // imgFlag.
                }
            }

        })
    }


}