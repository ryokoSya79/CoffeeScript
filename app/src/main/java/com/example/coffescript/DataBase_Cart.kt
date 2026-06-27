package com.example.coffescript

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database (entities = [Entity_Item::class], version = 4)
abstract class DataBase_Cart: RoomDatabase() {

    abstract fun getDao(): Dao
    companion object{
        fun getDb(context: Context): DataBase_Cart{
            return Room.databaseBuilder(context.applicationContext, DataBase_Cart::class.java, "Data_Cart.db" ).fallbackToDestructiveMigration().build()
        }
    }
}