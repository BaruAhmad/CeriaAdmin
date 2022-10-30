package com.skripsi.ceriaadmin.api

import com.skripsi.ceriaadmin.api.request.LoginRequest
import com.skripsi.ceriaadmin.api.response.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}