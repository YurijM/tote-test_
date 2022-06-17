package com.example.tote_test.ui.main.profile

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tote_test.R
import com.example.tote_test.database.UID
import com.example.tote_test.databinding.FragmentProfileBinding
import com.example.tote_test.models.GamblerModel
import com.example.tote_test.utils.APP_ACTIVITY
import com.example.tote_test.utils.GAMBLER
import com.example.tote_test.utils.checkProfile
import com.example.tote_test.utils.showToast

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        binding = FragmentProfileBinding.bind(view)

        setProfile()

        observeInProgress()

        binding.profileSave.setOnClickListener {
            saveProfile()
        }
    }

    private fun setProfile() {
        binding.profileInputNickname.setText(GAMBLER.nickname)
        binding.profileInputFamily.setText(GAMBLER.family)
        binding.profileInputName.setText(GAMBLER.name)
        binding.profileGenderGroup.check(
            if (GAMBLER.gender == "м") {
                binding.profileMan.id
            } else {
                binding.profileWoman.id
            }
        )
    }

    private fun saveProfile() {
        val gambler = GamblerModel()
        gambler.id = UID
        gambler.nickname = binding.profileInputNickname.text.toString().trim()
        gambler.family = binding.profileInputFamily.text.toString().trim()
        gambler.name = binding.profileInputName.text.toString().trim()
        val idCheckedButton = binding.profileGenderGroup.checkedRadioButtonId
        gambler.gender = binding.profileGenderGroup.findViewById<RadioButton>(idCheckedButton).text.toString()
        gambler.photoUrl = "qwerty"

        if (checkChanges(gambler)) {
            if (checkProfile(gambler)) {
                viewModel.update(gambler) {
                    showToast("Данные успешно сохранены")
                    APP_ACTIVITY.navController.navigate(R.id.action_navProfile_to_navGamblers)
                }
            } else {
                showToast(requireContext().getString(R.string.error_all_required))
            }
        } else {
            APP_ACTIVITY.navController.navigate(R.id.action_navProfile_to_navGamblers)
        }
    }

    private fun checkChanges(gambler: GamblerModel): Boolean {
        return (gambler.nickname != GAMBLER.nickname
                || gambler.family != GAMBLER.family
                || gambler.name != GAMBLER.name
                || gambler.gender != GAMBLER.gender
                || gambler.photoUrl != GAMBLER.photoUrl)
    }

    private fun observeInProgress() = viewModel.inProgress.observe(viewLifecycleOwner) {
        if (it) {
            binding.progressBar.visibility = View.VISIBLE
            binding.profileSave.isEnabled = false
        } else {
            binding.progressBar.visibility = View.INVISIBLE
            binding.profileSave.isEnabled = true
        }
    }
}