package com.example.myuserapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myuserapp.data.repository.UserRepository
import com.example.myuserapp.data.room.User
import com.example.myuserapp.data.room.UserDatabase
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        userRepository = UserRepository(userDao)
    }

    fun insertUser(user: User) = viewModelScope.launch {
        userRepository.insertUser(user)
    }

    fun validateUser(username: String, password: String, onResult: (Boolean) -> Unit) = viewModelScope.launch {
        val user = userRepository.validateUser(username, password)
        onResult(true)
    }
}
