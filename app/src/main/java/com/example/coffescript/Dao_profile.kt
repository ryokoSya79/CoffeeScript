package com.example.coffescript


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface Dao_profile {

    @Insert
    fun insertItem(item: Entity_Item_profile)

    @Query("SELECT * FROM items")
    fun getAllItems(): List<Entity_Item_profile>

    @Query("DELETE FROM items")
    fun deleteAllItems()

    // Сбрасываем счётчик автоинкремента
    @Query("DELETE FROM sqlite_sequence WHERE name = 'items'")
    fun resetAutoIncrement()

    @Query("DELETE FROM items WHERE id = :itemId")
    fun deleteItemById(itemId: Int)

    @Query("UPDATE items SET id_user = :newPrice WHERE id = :itemId")
    fun updateIdUser(itemId: Int, newPrice: String)

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
                Entity_Item_profile(
                    name = item.name,
                    email = item.email,
                    password = item.password,
                    id_user = item.id_user
                )
            )
        }
    }
}