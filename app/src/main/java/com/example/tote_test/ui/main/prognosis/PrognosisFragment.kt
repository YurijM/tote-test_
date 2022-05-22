package com.example.tote_test.ui.main.prognosis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tote_test.databinding.FragmentPrognosisBinding
import com.example.tote_test.utils.APP_ACTIVITY

class PrognosisFragment : Fragment() {

    private var _binding: FragmentPrognosisBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val prognosisViewModel =
            ViewModelProvider(this)[PrognosisViewModel::class.java]

        _binding = FragmentPrognosisBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textPrognosis: TextView = binding.textPrognosis
        prognosisViewModel.text.observe(viewLifecycleOwner) {
            textPrognosis.text = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}