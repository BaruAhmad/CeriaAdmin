package com.skripsi.ceriaadmin.api.response

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("id")
    var id: Int,
    @SerializedName("nip")
    var nip: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("image")
    var image: String
)
