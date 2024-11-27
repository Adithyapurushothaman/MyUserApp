package com.example.myuserapp.data.repository

import android.util.Log
import com.example.myuserapp.data.room.User
import com.example.myuserapp.data.room.UserDao

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user:User){
        userDao.insertUser(user)
    }

    suspend fun updateUser(email: String,password: String){
        userDao.updateUser(email,password)
    }
    suspend fun isValidUser(email: String, password: String): Boolean {
        val user = userDao.validateUser(email, password)
        Log.d("${user?.password}","user password")
        return user != null // Returns true if user exists, false otherwise
    }
}