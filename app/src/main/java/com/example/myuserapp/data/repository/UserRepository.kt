package com.example.myuserapp.data.repository

import com.example.myuserapp.data.room.User
import com.example.myuserapp.data.room.UserDao

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user:User){
        userDao.insertUser(user)
    }

    suspend fun validateUser(email: String,password: String){
        userDao.validateUser(email,password)
    }
}