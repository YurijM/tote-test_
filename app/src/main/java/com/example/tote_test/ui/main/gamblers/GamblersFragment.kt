package com.example.tote_test.ui.main.gamblers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tote_test.databinding.FragmentGamblersBinding

class GamblersFragment : Fragment() {

    private var _binding: FragmentGamblersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val gamblersViewModel =
            ViewModelProvider(this)[GamblersViewModel::class.java]

        _binding = FragmentGamblersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textGamblers: TextView = binding.textGamblers
        gamblersViewModel.text.observe(viewLifecycleOwner) {
            textGamblers.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}