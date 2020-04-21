package com.softaai.agrostardemoassignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.TestObserver
import com.softaai.agrostardemoassignment.model.User
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class UserViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var userViewModel: UserViewModel

    lateinit var userMutableLiveDataObserver: TestObserver<ArrayList<User>?>

    @Before
    fun setUp() {
        userViewModel = UserViewModel()
    }

    @Test
    fun `test user list when initialize`() {

        val usersList = arrayListOf(
            User("https://i.picsum.photos/id/10/300/300.jpg", "rahul nehul", "mumbai"),
            User("https://i.picsum.photos/id/20/300/300.jpg", "swapnil gandhi", "nagar"),
            User("https://i.picsum.photos/id/30/300/300.jpg", "pramod lad", "dhule"),
            User("https://i.picsum.photos/id/40/300/300.jpg", "sapna gholani", "nashik"),
            User("https://i.picsum.photos/id/50/300/300.jpg", "puja andhyal", "pune"),
            User("https://i.picsum.photos/id/60/300/300.jpg", "akshay pawal", "beed"),
            User("https://i.picsum.photos/id/70/300/300.jpg", "amol gavali", "satara"),
            User("https://i.picsum.photos/id/80/300/300.jpg", "sarang gurav", "sangli"),
            User("https://i.picsum.photos/id/90/300/300.jpg", "sonam kapoor", "pune"),
            User("https://i.picsum.photos/id/100/300/300.jpg", "ashwini mali", "latur")
        )

        userMutableLiveDataObserver = TestObserver.test(userViewModel.userMutableLiveData)

        userViewModel.init()

        assertEquals(usersList, userMutableLiveDataObserver.value())
    }


}