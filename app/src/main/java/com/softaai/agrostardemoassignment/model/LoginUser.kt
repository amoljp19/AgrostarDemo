package com.softaai.agrostardemoassignment.model


data class LoginUser(val strUsername: String, val strPassword: String) {

    val isUsernameValid: Boolean
        get() = strUsername.equals("admin")

    val isPasswordValid: Boolean
        get() = strPassword.equals("admin")
}