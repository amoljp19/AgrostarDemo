package com.softaai.agrostardemoassignment.view

import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import com.softaai.agrostardemoassignment.R

@DslMarker
private annotation class TestRobotMarker

@TestRobotMarker
private interface LoginRobot

class LoginTestRobot : LoginRobot {

    companion object {

        fun loginScreen(block: LoginTestRobot.() -> Unit): LoginTestRobot {
            return LoginTestRobot().apply(block)
        }
    }


    fun includesUsername() = R.id.txtUsername check isDisplayed

    fun includesPassword() = R.id.txtPassword check isDisplayed

    fun includesLoginButton() = R.id.btnLogin check isDisplayed

    fun typeUsername(username: String) = R.id.txtUsername perform typeText(username)

    fun typePassword(password: String) = R.id.txtPassword perform typeText(password)

    fun usernameEditor(block: UsernameRobot.() -> Unit): UsernameRobot {
        return UsernameRobot().apply(block)
    }

    fun passwordEditor(block: PasswordRobot.() -> Unit): PasswordRobot {
        return PasswordRobot().apply(block)
    }

    fun loginButton(block: LoginButtonRobot.() -> Unit): LoginButtonRobot {
        return LoginButtonRobot().apply(block)
    }

    infix fun login(block: LoginResult.() -> Unit): LoginResult {
        R.id.btnLogin perform ViewActions.click()
        return LoginResult().apply(block)
    }
}

class UsernameRobot : LoginRobot {

    fun hasCorrectHint() = R.id.txtUsername hasHint R.string.username
}

class PasswordRobot : LoginRobot {

    fun hasCorrectHint() = R.id.txtPassword hasHint R.string.password
}

class LoginButtonRobot : LoginRobot {

    fun hasCorrectTitle() = R.id.btnLogin hasText R.string.login
}

class LoginResult : LoginRobot {

    fun displaysInvalidUsernameError() = R.id.txtUsername.check(
        ViewAssertions.matches(
            hasErrorText(
                "Enter a valid username"
            )
        )
    )

    fun displaysInvalidPasswordError() = R.id.txtPassword.check(
        ViewAssertions.matches(
            hasErrorText(
                "Enter a valid password"
            )
        )
    )

    fun opensMainScreen() = R.id.collapsing_toolbar hasText R.string.collapsing_toolbar_title
}