package com.a.ayc.user.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.a.ayc.R
import com.a.ayc.general.GeneralStrings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sign_up.*
import java.util.*

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpViewModel.currentUser.observe(viewLifecycleOwner, { result ->
            if (result == null || result.exception?.message != null) {
                Toast.makeText(
                    requireContext(),
                    "Failed : ${result.exception?.message}",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                signUpViewModel.setUserInfo(
                    signUp_et_4.editText?.text.toString(),
                    signUp_et_1.editText?.text.toString()
                )
                findNavController().navigate(
                    SignUpFragmentDirections.actionGlobalHomeFragment(
                        GeneralStrings.keySignUp
                    )
                )
            }
        })

        btn_sign_up.setOnClickListener {
            val email = signUp_et_1.editText?.text.toString()
            val password = signUp_et_2.editText?.text.toString()
            val repeatPassword = signUp_et_3.editText?.text.toString()
            val name = signUp_et_4.editText?.text.toString()
            if (validateInputs(email.toLowerCase(Locale.ROOT), password, repeatPassword, name)) {
                signUpViewModel.signUp(email.toLowerCase(Locale.ROOT), password)
            }
        }
    }

    private fun validateInputs(
        email: String,
        password: String,
        repeatPassword: String,
        name: String
    ) = signUpViewModel.validateName(name) &&
            signUpViewModel.validateEmail(email) &&
            signUpViewModel.validatePassword(password) &&
            signUpViewModel.validateTheSamePassword(password, repeatPassword)


}