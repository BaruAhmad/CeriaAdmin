package com.skripsi.ceriaadmin.api.request

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("nip")
    var nip: String,
    @SerializedName("password")
    var password: String
)