package com.example.tote_test.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tote_test.R
import com.example.tote_test.databinding.FragmentSplashBinding
import com.example.tote_test.ui.main.MainActivity

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private var _binding: FragmentSplashBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        return binding.root
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSplashBinding.bind(view)

        renderAnimations()

        renderAnimationsUp()

        launchMainScreen(false)
    }

    private fun launchMainScreen(isSignedIn: Boolean) {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            //finish()
        }, 10000)

        /*val intent = Intent(requireContext(), MainActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        val args = MainActivityArgs(isSignedIn)
        intent.putExtras(args.toBundle())
        startActivity(intent)*/
    }

    private fun renderAnimations() {
        binding.splashLogo.apply {
            alpha = 0f
            animate()
                .alpha(1f)
                .setStartDelay(500)
                .setDuration(5000)
                .start()
        }
    }

    private fun renderAnimationsUp() {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.splashLogo.apply {
                alpha = 1f
                animate()
                    .alpha(0f)
                    .setStartDelay(500)
                    .setDuration(5000)
                    .start()
            }
        }, 5000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}