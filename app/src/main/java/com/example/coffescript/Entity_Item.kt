package com.example.coffescript

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "items")
data class Entity_Item(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "name")
    var name_product: String,
    @ColumnInfo(name = "title_elem")
    var title_elem: String,
    @ColumnInfo(name = "title_price")
    var title_price: String,
    @ColumnInfo(name = "title_kol")
    var title_kol: String,
    @ColumnInfo(name = "id_image")
    var id_image: String

)