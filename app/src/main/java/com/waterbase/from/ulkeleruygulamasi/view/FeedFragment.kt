package com.waterbase.from.ulkeleruygulamasi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.waterbase.from.ulkeleruygulamasi.adapter.CountryAdapter
import com.waterbase.from.ulkeleruygulamasi.databinding.FragmentFeedBinding
import com.waterbase.from.ulkeleruygulamasi.viewmodel.FeedViewModel


class FeedFragment : Fragment() {

    private lateinit var viewModel: FeedViewModel
    private val countryAdapter = CountryAdapter()


    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()


        binding.apply {
            rvCountryList.layoutManager = LinearLayoutManager(context)
            rvCountryList.adapter = countryAdapter

            swipeRefreshLayout.setOnRefreshListener {
                rvCountryList.visibility = View.GONE
                tvCountryError.visibility = View.GONE
                progressBarCountryLoading.visibility = View.VISIBLE
                swipeRefreshLayout.isRefreshing = false
                viewModel.refreshData()
            }

            observeLiveData()


        }
    }

    fun observeLiveData() {
        // liveData'ları gözlemleyeceğiz
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                binding.rvCountryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }

        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it)
                    binding.tvCountryError.visibility = View.VISIBLE
                else
                    binding.tvCountryError.visibility = View.GONE
            }

        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    binding.progressBarCountryLoading.visibility = View.VISIBLE
                    binding.tvCountryError.visibility = View.GONE
                    binding.rvCountryList.visibility = View.GONE
                } else {
                    binding.progressBarCountryLoading.visibility = View.GONE

                }
            }

        })
    }
}