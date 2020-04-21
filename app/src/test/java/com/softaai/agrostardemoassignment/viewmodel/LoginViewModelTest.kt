package com.softaai.agrostardemoassignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.TestObserver
import com.softaai.agrostardemoassignment.model.LoginUser
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class LoginViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var validLoginUser: LoginUser

    lateinit var loginViewModel: LoginViewModel

    lateinit var loginUserMutableLiveDataObserver: TestObserver<LoginUser>


    @Before
    fun setUp() {
        loginViewModel = LoginViewModel()
        validLoginUser = LoginUser("admin", "admin")
    }


    @Test
    fun `test live data not null`() {
        MatcherAssert.assertThat(loginViewModel.Username, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loginViewModel.Password, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loginViewModel.loginUser, CoreMatchers.notNullValue())
    }

    @Test
    fun `test login user live data`() {
        loginViewModel.Username.value = "admin"
        loginViewModel.Password.value = "admin"
        loginUserMutableLiveDataObserver = TestObserver.test(loginViewModel.loginUser)

        loginViewModel.onClick()

        assertEquals(validLoginUser, loginUserMutableLiveDataObserver.value())
    }

    @Test
    fun `test username and password is valid`() {
        loginViewModel.loginUser.value = validLoginUser
        Assert.assertTrue(loginViewModel.loginUser.value!!.isUsernameValid)
        Assert.assertTrue(loginViewModel.loginUser.value!!.isPasswordValid)
    }

    @Test
    fun `test username and password is invalid`() {
        val invalidLoginUser = LoginUser("abc", "xyz")
        loginViewModel.loginUser.value = invalidLoginUser
        Assert.assertFalse(loginViewModel.loginUser.value!!.isUsernameValid)
        Assert.assertFalse(loginViewModel.loginUser.value!!.isPasswordValid)
    }

    @Test
    fun `test username and password is empty`() {
        val emptyLoginUser = LoginUser("", "")
        loginViewModel.loginUser.value = emptyLoginUser
        Assert.assertThat(loginViewModel.loginUser.value!!.strUsername, CoreMatchers.`is`(""))
        Assert.assertThat(loginViewModel.loginUser.value!!.strPassword, CoreMatchers.`is`(""))
    }

}