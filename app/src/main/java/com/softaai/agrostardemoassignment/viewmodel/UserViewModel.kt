package com.softaai.agrostardemoassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softaai.agrostardemoassignment.model.User

class UserViewModel : ViewModel() {
    var userMutableLiveData: MutableLiveData<ArrayList<User>?>
    lateinit var userArrayList: ArrayList<User>

    fun init() {
        userArrayList = populateList()
        userMutableLiveData.value = userArrayList
    }

    fun populateList(): ArrayList<User> {

        val users = arrayListOf(
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

        return users
    }

    init {
        userMutableLiveData = MutableLiveData()

        init()
    }
}