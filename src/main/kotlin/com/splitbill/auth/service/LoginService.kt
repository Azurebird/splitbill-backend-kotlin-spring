package com.splitbill.auth.service

interface LoginService {
     fun createLogin(userId: String, email: String, password: String)
}