package com.example.lesson17.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.lesson17.R
import com.example.lesson17.models.UserAuth
import com.example.lesson17.mvvm.auth.AuthRepository
import com.example.lesson17.mvvm.auth.AuthViewModel
import com.example.lesson17.mvvm.auth.AuthViewModelFactory
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    private lateinit var viewModel: AuthViewModel
    private lateinit var loginTextInput: TextInputLayout
    private lateinit var passwordTextInput: TextInputLayout
    private lateinit var submitBtn: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginTextInput = view.findViewById(R.id.loginLoginTextInput)
        passwordTextInput = view.findViewById(R.id.loginPasswordTextInput)
        submitBtn = view.findViewById(R.id.loginBtn)
        submitBtn.setOnClickListener {
            val username = loginTextInput.editText?.text.toString()
            val pwd = passwordTextInput.editText?.text.toString()
            viewModel.submitAuthInfo(UserAuth(username, pwd))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val provider = ViewModelProvider(this, AuthViewModelFactory(AuthRepository()))
        viewModel = provider[AuthViewModel::class.java]
        initObserves()
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private fun initObserves() {
        viewModel.authLiveData.observe(viewLifecycleOwner) {
            if (it.authSuccess) {
                viewModel.userAuthDone()
                parentFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainerView, CountriesFragment.newInstance())
                    .addToBackStack("")
                    .commit()
            } else {
                loginTextInput.editText?.setText(it.userName)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}