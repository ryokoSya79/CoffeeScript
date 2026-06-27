package com.example.coffescript.Model

data class GetProduct(
    val status: String? = null,
    val count: Int? = null,
    val products: List<Product>? = null
//    val name_product: String? = null,
//    val image_product: String? = null,
//    val data_age: String? = null,
//    val type: String? = null
)