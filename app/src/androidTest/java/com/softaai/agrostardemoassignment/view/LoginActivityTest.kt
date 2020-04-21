package com.softaai.agrostardemoassignment.view

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.softaai.agrostardemoassignment.view.LoginTestRobot.Companion.loginScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)


    @Test
    fun shouldContainUsernameInput() {
        loginScreen {
            includesUsername()
        }
    }

    @Test
    fun shouldUsernameInputApplyCorrectHint() {
        loginScreen {
            usernameEditor {
                hasCorrectHint()
            }
        }
    }

    @Test
    fun shouldContainPasswordInput() {
        loginScreen {
            includesPassword()
        }
    }

    @Test
    fun shouldPasswordInputApplyCorrectHint() {
        loginScreen {
            passwordEditor {
                hasCorrectHint()
            }
        }
    }

    @Test
    fun shouldContainLoginButton() {
        loginScreen {
            includesLoginButton()
        }
    }

    @Test
    fun shouldLoginButtonApplyCorrectText() {
        loginScreen {
            loginButton {
                hasCorrectTitle()
            }
        }
    }

    @Test
    fun shouldLoginWithInvalidUsernameDisplayError() {

        loginScreen {
            typeUsername("abc")
            typePassword("admin")
        } login {
            displaysInvalidUsernameError()
        }
    }

    @Test
    fun shouldLoginWithInvalidPasswordDisplayError() {
        loginScreen {
            typeUsername("admin")
            typePassword("xyz")
        } login {
            displaysInvalidPasswordError()
        }
    }

    @Test
    fun successfulLoginShouldOpenMainScreen() {
        loginScreen {
            typeUsername("admin")
            typePassword("admin")
        } login {
            opensMainScreen()
        }
    }
}