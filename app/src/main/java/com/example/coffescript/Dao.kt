package com.example.coffescript


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface Dao {

    @Insert
    fun insertItem(item: Entity_Item)

    @Query("SELECT * FROM items")
    fun getAllItems(): List<Entity_Item>

    @Query("DELETE FROM items")
    fun deleteAllItems()

    // Сбрасываем счётчик автоинкремента
    @Query("DELETE FROM sqlite_sequence WHERE name = 'items'")
    fun resetAutoIncrement()

    @Query("DELETE FROM items WHERE id = :itemId")
    fun deleteItemById(itemId: Int)

    @Query("UPDATE items SET title_price = :newPrice WHERE id = :itemId")
    fun updatePriceById(itemId: Int, newPrice: String)

    @Query("UPDATE items SET title_kol = :newPrice WHERE id = :itemId")
    fun updateKolById(itemId: Int, newPrice: String)

    // Метод для перестройки ID
    @Transaction
    fun deleteAndRebuild(itemId: Int) {
        deleteItemById(itemId)
        val items = getAllItems()
        deleteAllItems()
        resetAutoIncrement()

        // Создаем новые записи без ID (Room автоматически присвоит новые)
        items.forEach { item ->
            insertItem(
                Entity_Item(
                    name_product = item.name_product,
                    title_price = item.title_price,
                    title_elem = item.title_elem,
                    id_image = item.id_image,
                    title_kol = item.title_kol
                )
            )
        }
    }
}