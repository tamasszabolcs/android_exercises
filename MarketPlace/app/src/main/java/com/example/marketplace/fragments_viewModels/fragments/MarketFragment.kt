package com.example.marketplace.fragments_viewModels.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.databinding.FragmentMarketBinding
import com.example.marketplace.fragments_viewModels.ViewModels.MarketViewModel
//import com.example.marketplace.activities.databinding.FragmentDashboardBinding


class MarketFragment : Fragment() {

    private lateinit var marketViewModel: MarketViewModel
    private var _binding: FragmentMarketBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        marketViewModel =
            ViewModelProvider(this).get(MarketViewModel::class.java)

        _binding = FragmentMarketBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        marketViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}