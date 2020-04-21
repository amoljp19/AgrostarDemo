package com.softaai.agrostardemoassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softaai.agrostardemoassignment.model.LoginUser


class LoginViewModel : ViewModel() {
    var Username = MutableLiveData<String?>()
    var Password = MutableLiveData<String?>()
    private var loginUserMutableLiveData: MutableLiveData<LoginUser>? = null
    val loginUser: MutableLiveData<LoginUser>
        get() {
            if (loginUserMutableLiveData == null) {
                loginUserMutableLiveData = MutableLiveData()
            }
            return loginUserMutableLiveData!!
        }

    fun onClick() {
        if (!Username.value.isNullOrEmpty() && !Password.value.isNullOrEmpty()) {
            val loginUser = LoginUser(Username.value!!, Password.value!!)
            loginUserMutableLiveData!!.setValue(loginUser)
        }
    }
}
