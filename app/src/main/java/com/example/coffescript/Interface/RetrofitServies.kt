package com.example.coffescript.Interface


import com.example.coffescript.Model.GetProduct
import retrofit2.http.GET

interface RetrofitServies {
    @GET("/")
    suspend fun getProductById(): GetProduct
}