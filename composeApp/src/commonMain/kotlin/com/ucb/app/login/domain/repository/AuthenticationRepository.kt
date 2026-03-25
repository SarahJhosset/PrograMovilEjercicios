package com.ucb.app.login.domain.repository

import com.ucb.app.login.domain.model.LoginModel
import com.ucb.app.login.domain.model.UserModel

interface AuthenticationRepository {
    // Ahora devuelve UserModel en lugar de Unit
    // porque necesitamos saber quién es el usuario tras el login
    suspend fun login(model: LoginModel): UserModel
}