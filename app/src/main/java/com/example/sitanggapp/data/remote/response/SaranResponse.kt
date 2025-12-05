package com.example.sitanggapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class SaranResponse(
    @field:SerializedName("id_saran")
    val idSaran: Int? = null,

    @field:SerializedName("judul")
    val judul: String? = null,

    @field:SerializedName("deskripsi")
    val deskripsi: String? = null,

    @field:SerializedName("foto")
    val foto: String? = null,

    @field:SerializedName("latitude")
    val latitude: Double? = null,

    @field:SerializedName("longitude")
    val longitude: Double? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)
