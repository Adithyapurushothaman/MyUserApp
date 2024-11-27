package com.example.myuserapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myuserapp.data.repository.UserRepository
import com.example.myuserapp.data.room.User
import com.example.myuserapp.data.room.UserDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        userRepository = UserRepository(userDao)
        Log.d("userDao is created","user db created")
    }

    fun insertUser(user: User) = viewModelScope.launch {
        userRepository.insertUser(user)
    }
    fun updateUser(email:String,password: String) = viewModelScope.launch {
        userRepository.updateUser(email, password)
    }

    fun validateUser(email: String,password: String) : Boolean {
        return runBlocking {
            userRepository.isValidUser(email, password)
        }
    }
}
