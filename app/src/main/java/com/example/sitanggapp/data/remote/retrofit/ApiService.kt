package com.example.sitanggapp.data.remote.retrofit

import com.example.sitanggapp.data.remote.response.LoginResponse
import com.example.sitanggapp.data.remote.response.RegisterResponse
import com.example.sitanggapp.data.remote.response.SaranResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    // 1. GET All Saran
    @GET("saran")
    suspend fun getAllSaran(): Response<List<SaranResponse>>

    // 2. POST Saran (Multipart karena ada upload foto)
    @Multipart
    @POST("saran")
    suspend fun addSaran(
        @Part("judul") judul: String,
        @Part("deskripsi") deskripsi: String,
        @Part("latitude") latitude: String? = null,
        @Part("longitude") longitude: String? = null,
        @Part foto: MultipartBody.Part?
    ): Response<SaranResponse>

    // 3. DELETE Saran
    @DELETE("saran/{id}")
    suspend fun deleteSaran(
        @Path("id") id: Int
    ): Response<SaranResponse>

    @Multipart
    @POST("saran/{id}") // Atau gunakan @PUT("saran/{id}") tergantung backend kamu
    suspend fun updateSaran(
        @Path("id") id: Int, // Tambahkan ID agar server tahu data mana yang diedit
        @Part("judul") judul: String,
        @Part("deskripsi") deskripsi: String,
        @Part("latitude") latitude: String? = null,
        @Part("longitude") longitude: String? = null,
        @Part foto: MultipartBody.Part? // Foto bisa null jika user tidak update foto
    ): Response<SaranResponse>

}
