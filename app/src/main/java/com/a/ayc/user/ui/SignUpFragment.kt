package com.a.ayc.user.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.a.ayc.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sign_up.*

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
                Toast.makeText(
                    requireContext(),
                    "navigated : ${result.result!!.user!!.uid}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        btn_sign_up.setOnClickListener {
            val email = signUp_et_1.editText?.text.toString()
            val password = signUp_et_2.editText?.text.toString()
            val repeatPassword = signUp_et_3.editText?.text.toString()
            if (validateInputs(email, password, repeatPassword)) {
                signUpViewModel.signUp(email, password)
            }
        }
    }

    private fun validateInputs(
        email: String,
        password: String,
        repeatPassword: String
    ) = signUpViewModel.validateEmail(email) &&
            signUpViewModel.validatePassword(password) &&
            signUpViewModel.validateTheSamePassword(password, repeatPassword)

}