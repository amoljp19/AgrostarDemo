package com.softaai.agrostardemoassignment.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.softaai.agrostardemoassignment.R
import com.softaai.agrostardemoassignment.databinding.ActivityLoginBinding
import com.softaai.agrostardemoassignment.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() {
    private var loginViewModel: LoginViewModel? = null
    private var binding: ActivityLoginBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding = DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_login)
        binding?.setLifecycleOwner(this)
        binding?.setLoginViewModel(loginViewModel)

        loginViewModel!!.loginUser.observe(this, Observer {
            if (TextUtils.isEmpty(it.strUsername) || !it.isUsernameValid) {
                binding?.txtUsername?.setError(getString(R.string.errorInvalidUsername))
                binding?.txtUsername?.requestFocus()
            } else if (TextUtils.isEmpty(it.strPassword) || !it.isPasswordValid) {
                binding?.txtPassword?.setError(getString(R.string.errorInvalidPassword))
                binding?.txtPassword?.requestFocus()
            } else {
                goToMainActivity()
            }
        })
    }

    private fun goToMainActivity() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }
}

