package com.ibrahim.agrigrow.repository

import com.ibrahim.agrigrow.data.UserDao
import com.ibrahim.agrigrow.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        userDao.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)
    }
}