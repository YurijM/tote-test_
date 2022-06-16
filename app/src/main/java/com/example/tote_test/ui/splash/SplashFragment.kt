package com.example.tote_test.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tote_test.R
import com.example.tote_test.database.FirebaseRepository
import com.example.tote_test.database.REPOSITORY
import com.example.tote_test.database.UID
import com.example.tote_test.databinding.FragmentSplashBinding
import com.example.tote_test.ui.main.MainActivity
import com.example.tote_test.utils.GAMBLER
import com.example.tote_test.utils.START_FRAGMENT
import com.example.tote_test.utils.checkProfile

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var vmSplash: SplashViewModel
    private var _binding: FragmentSplashBinding? = null
    private var animationDuration = 2500L
    private var animationStart = 500L

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSplashBinding.bind(view)

        initDatabase()

        vmSplash = ViewModelProvider(this)[SplashViewModel::class.java]
        vmSplash.getGambler()
        vmSplash.currentGambler.observe(viewLifecycleOwner, Observer {
            GAMBLER = it

            START_FRAGMENT = getStartFragment()
        })

        renderAnimations()

        renderAnimationsUp()

        launchMainScreen(false)
    }

    private fun initDatabase() {
        REPOSITORY = FirebaseRepository()
        REPOSITORY.initFirebase()
    }

    private fun getStartFragment(): Int {
        val idLayout: Int

        if (UID == "null") {
            idLayout = R.id.navLogin
        } else if (!checkProfile()) {
            idLayout = R.id.navProfile
        } else {
            idLayout = R.id.navGamblers
        }

        return idLayout
    }

    private fun launchMainScreen(isSignedIn: Boolean) {
        val delay = animationDuration * 2

        Handler(Looper.getMainLooper()).postDelayed({
            Intent(requireContext(), MainActivity::class.java).also {
                startActivity(it)
                parentFragment?.activity?.finish()
            }
        }, delay)

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
                .setStartDelay(animationStart)
                .setDuration(animationDuration)
                .start()
        }
    }

    private fun renderAnimationsUp() {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.splashLogo.apply {
                alpha = 1f
                animate()
                    .alpha(0f)
                    .setStartDelay(animationStart)
                    .setDuration(animationDuration)
                    .start()
            }
        }, animationDuration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}